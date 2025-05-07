package com.tasktogether.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tasktogether.dto.MinUserInfo;
import com.tasktogether.dto.PUTUserDTO;
import com.tasktogether.dto.ProjectSimpleDTO;
import com.tasktogether.dto.UserChangeRole;
import com.tasktogether.dto.UserDTO;
import com.tasktogether.dto.UserSimpleDTO;
import com.tasktogether.dto.access.LoginDTO;
import com.tasktogether.dto.access.RegisterDTO;
import com.tasktogether.model.User;
import com.tasktogether.security.TokenUtils;
import com.tasktogether.service.EmailService;
import com.tasktogether.service.UserService;

import io.jsonwebtoken.MalformedJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios salvo login y register")
public class UsuarioController {

	@Autowired
	UserService usuarioService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailservice;
	
//	@Autowired
//	private CloudinaryLib cloudinarylib;

	@GetMapping("/users")
	@Operation(summary = "Obtener una lista de usuarios", description = "Devuelve todos los usuarios de la base de datos")
	@ApiResponses({ @ApiResponse(responseCode = "401", description = "Token requerido"),
			@ApiResponse(responseCode = "200", description = "Usuarios encontrados") })
	public ResponseEntity<?> listUsers(@RequestHeader("Authorization") String token) {
		// Validar si el token es válido (esto dependerá de tu lógica de autenticación)
		if (token == null || token.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is required");
		}

		List<User> users = usuarioService.getUsers();
		List<MinUserInfo> usersDTO = usuarioService.parseToMinInfo(users);

		// No tengo que quitarle el "Bearer " para que vaya
		return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
	}

	@GetMapping("/users/{id}")
	@Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario basado en su ID")
	@ApiResponses({ @ApiResponse(responseCode = "401", description = "Token requerido"),
			@ApiResponse(responseCode = "200", description = "Usuario encontrado"),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado") })
	public ResponseEntity<?> findUser(
			@Parameter(description = "token del usuario", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "ID del usuario", example = "1", required = true) @PathVariable Long id) {
		if (token == null || token.isEmpty()) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "403");
			body.put("message", "Token is required");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		}

		try {
			User u = usuarioService.findUser(id);
			UserDTO udto = usuarioService.parseToDTO(u);
			return ResponseEntity.status(HttpStatus.OK).body(udto);
		} catch (Exception e) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "404");
			body.put("message", "User not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
		}

	}

	//Para obtener los datos de un usuario dado el email
	@GetMapping("/api/users/{email}")
	public ResponseEntity<?> getUserByEmail(@RequestHeader("Authorization") String token,
			@PathVariable String email) {
		if (token == null || token.isEmpty()) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "403");
			body.put("message", "Token is required");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		}
		
		try {
			//Decodifico el token para asegurarme de que
			UsernamePasswordAuthenticationToken auth = TokenUtils.decodeToken(token);
			String role = auth.getAuthorities().iterator().next().getAuthority();
			String principal = auth.getName(); //Me va a dar el email
			
			if (!role.equals("GEN_ADMIN") && principal != email) {
				Map<String, String> body = new HashMap<String, String>();
				body.put("error", "403");
				body.put("message", "You don´t have permission for this");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
			}
			
			User u = usuarioService.findUserByUsername(principal);
			
			if (u == null) {
				Map<String, String> body = new HashMap<String, String>();
				body.put("error", "404");
				body.put("message", "User don´t exists");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
			}
			
			UserSimpleDTO usdto = new UserSimpleDTO(u);
			
			return ResponseEntity.status(HttpStatus.OK).body(usdto);
			
		} catch (MalformedJwtException e) {
			System.err.println(e.getMessage());
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "400");
			body.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "500");
			body.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}
		
	}
	
	
	@PutMapping(value="/users/{id}", 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@Operation(summary = "Editar usuario por ID", description = "Devuelve al usuario editado")
	@ApiResponses({ @ApiResponse(responseCode = "401", description = "Token requerido"),
			@ApiResponse(responseCode = "200", description = "Usuario editado"),
			@ApiResponse(responseCode = "401", description = "No autorizado") })
	public ResponseEntity<?> editUser(
			@Parameter(description = "token del usuario", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "ID del usuario", example = "1", required = true) @PathVariable Long id,
			@Parameter(description = "Datos a editar del usuario", required = true) @RequestPart("put") PUTUserDTO put,
			@RequestPart("profile_pic") MultipartFile file) {
		if (token == null || token.isEmpty()) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "403");
			body.put("message", "Token is required");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		} else {
			UsernamePasswordAuthenticationToken authentication = TokenUtils.decodeToken(token);
			String role = authentication.getAuthorities().iterator().next().getAuthority();

			User u = usuarioService.findUser(id);

			if (u != null) {
				if ("GEN_ADMIN".equals(role)) {
					User newUser = usuarioService.parseToUser(put);
					newUser.setId(u.getId());
					newUser.setEmail(u.getEmail());
					String raw = put.getPassword();
					if (raw != null && !raw.isBlank()) {
					   newUser.setPassword(passwordEncoder.encode(raw));
					} else {
					   newUser.setPassword(u.getPassword());
					}
					try {
						newUser.setProfile_pic(usuarioService.uploadFile(file));
					} catch (Exception e) {
						Map<String, String> body = new HashMap<String, String>();
						body.put("error", "500");
						body.put("message", e.getMessage());
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
					}
					
					User response = usuarioService.save(newUser);
					return ResponseEntity.status(HttpStatus.OK).body(usuarioService.parseToDTO(response));
				} else {
					String username = authentication.getName();
					if (u.getEmail().equals(username)) {
						User newUser = usuarioService.parseToUser(put);
						newUser.setId(u.getId());
						newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
						newUser.setRole(u.getRole());
						User response = usuarioService.save(newUser);
						return ResponseEntity.status(HttpStatus.OK).body(usuarioService.parseToDTO(response));
					} else {
						Map<String, String> body = new HashMap<String, String>();
						body.put("error", "403");
						body.put("message", "You don't have permission for this");
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
					}

				}

			} else {
				Map<String, String> body = new HashMap<String, String>();
				body.put("error", "404");
				body.put("message", "User not found or not exist");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
			}

		}

	}
	
	@PutMapping("/users/role/{id}")
	public ResponseEntity<?> changeUserRole(@RequestHeader("Authorization") String token,
			@PathVariable Long id, @RequestBody UserChangeRole newRole) {
		if (token == null || token.isEmpty()) {
			/*Como la respuesta en este caso es siempre la misma decidí hacerlo así
			 * para tratar de mantener un código más limpio*/
			return usuarioService.noTokenResponse();
		} else {
			try {
				//Arreglar excepción token mal formado
				UsernamePasswordAuthenticationToken authentication = TokenUtils.decodeToken(token);
				String role = authentication.getAuthorities().iterator().next().getAuthority();

				User u = usuarioService.findUser(id);

				if (u != null) {
					if ("GEN_ADMIN".equals(role)) {
						u.setRole(newRole.getNewRole());
						User response = usuarioService.save(u);
						return ResponseEntity.status(HttpStatus.OK).body(usuarioService.parseToDTO(response));
					} else {
						Map<String, String> body = new HashMap<String, String>();
						body.put("error", "403");
						body.put("message", "You don't have permission for this");
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);

					}

				} else {
					Map<String, String> body = new HashMap<String, String>();
					body.put("error", "404");
					body.put("message", "User not found or not exist");
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
				}
			} catch (MalformedJwtException e) {
				// TODO Auto-generated catch block
				Map<String, String> body = new HashMap<String, String>();
				body.put("error", "400");
				body.put("message", e.getMessage());
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
			}
		}
		
	}

	@DeleteMapping("/users/{id}")
	@Operation(summary = "Borrar un usuario dado su id", description = "Devuelve los datos del usuario que se acaba de borrar")
	@ApiResponses({ @ApiResponse(responseCode = "401", description = "Token requerido"),
			@ApiResponse(responseCode = "200", description = "Usuario eliminado"),
			@ApiResponse(responseCode = "401", description = "No autorizado") })
	public ResponseEntity<?> deleteUser(
			@Parameter(description = "token del usuario", required = true) @RequestHeader("Authorization") String token,
			@Parameter(description = "ID del usuario", example = "1", required = true) @PathVariable Long id) {
		if (token == null || token.isEmpty()) {
			Map<String, String> body = new HashMap<String, String>();
			body.put("error", "403");
			body.put("message", "Token is required");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		} else {

			UsernamePasswordAuthenticationToken authentication = TokenUtils.decodeToken(token);
			String role = authentication.getAuthorities().iterator().next().getAuthority();

			if ("GEN_ADMIN".equals(role)) {
				User u = usuarioService.findUser(id);

				if (u != null) {
					usuarioService.delete(u);
					UserDTO dto = usuarioService.parseToDTO(u);
					return ResponseEntity.status(HttpStatus.OK).body(dto);
				} else {
					Map<String, String> body = new HashMap<String, String>();
					body.put("error", "404");
					body.put("message", "User not found or may not exist");
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
				}

			} else {
				Map<String, String> body = new HashMap<String, String>();
				body.put("error", "403");
				body.put("message", "You don't have permission to do this");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
			}
		}

	}

	@PostMapping("/signin")
	@Operation(summary = "Iniciar Sesión", description = "Devuelve un token de inicio de sesión")
	@ApiResponses({

			@ApiResponse(responseCode = "200", description = "Sesión iniciada")

	})
	public ResponseEntity<?> authenticateUser(
			@Parameter(description = "Credenciales de login", required = true) @RequestBody LoginDTO loginRequest)
			throws Exception {
//		Authentication authentication;
		String jwt = null;
		Map<String, String> token = new HashMap<String, String>();
		// Si el usuario y el password que le paso son los adecuados me
		// devuele un autentication. Si no lo encuentra, lanza una exception
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			User user = (User) authentication.getPrincipal();
			jwt = TokenUtils.generateToken(loginRequest.getEmail(), user.getEmail(), user.getRole());
		} catch (Exception e) {
			token.put("error", "401");
			token.put("message", "Bad Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(token);
		}

		token.put("token", jwt);
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	@Operation(summary = "Registrar a un usuario", description = "Devuelve los datos del usuario registrado")
	 @ApiResponses({
		    
		    @ApiResponse(responseCode = "200", description = "Usuario registrado")
		    
		 })
	public ResponseEntity<?> registerUser(
			 @Parameter(description = "Credenciales de registro", required = true) @RequestBody RegisterDTO register) throws Exception {
		
		if (usuarioService.checkMailIsUnique(register.getEmail())) {
			Map<String, String> response = new HashMap<>();
			response.put("error", "400");
			response.put("message", "The introduced email is already registered");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		User u = usuarioService.save(new User(register.getName(), "USER", register.getAddress(), 
				register.getEmail(), passwordEncoder.encode(register.getPassword()), register.getJob()));

		if (u != null) {
			
			UserDTO dto = new UserDTO(u, new ArrayList<ProjectSimpleDTO>());
			emailservice.sendVerification(u);
			return ResponseEntity.ok(dto);
		} else {
			throw new Exception();
		}
		
		
	}
	
	
	//Por si acaso necesito codificar las contraseñas
//	@GetMapping("/usersPass")
//	public ResponseEntity<?> listUsersPass() {
//		List<User> users = usuarioService.getUsers();
//		for(User usuario :users) {
//			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
//			usuarioService.save(usuario);
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(users);
//
//	}

}
