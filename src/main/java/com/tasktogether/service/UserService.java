package com.tasktogether.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tasktogether.dto.MinUserInfo;
import com.tasktogether.dto.PUTUserDTO;
import com.tasktogether.dto.ProjectSimpleDTO;
import com.tasktogether.dto.UserDTO;
import com.tasktogether.model.Project;
import com.tasktogether.model.User;
import com.tasktogether.repository.UserRepository;

@Service
@Primary
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userData;
	
	@Autowired
	Cloudinary cloudinaryConfig;
	
	public List<User> getUsers() {
		return userData.findAll();
	}

	public User findUser(Long id) {
		return userData.findById(id).orElse(null);
	}
	
	public User findUserByUsername(String username) {
		return userData.findByEmailLike(username).get(0);
	}
	
	public User save(User u) {
		return userData.save(u);
	}
	
	public void delete(User u) {
		userData.delete(u);
	}
	
	public boolean checkMailIsUnique(String email) {
		
		List<User> usersEmailLike = userData.findByEmailLike(email);
		
		return usersEmailLike.size() > 0;
		
	}
	
	public UserDTO parseToDTO(User u) {
		
		List<ProjectSimpleDTO> projects = u.getProjects() != null ? parseToSimpleDTO(u.getProjects()) : new ArrayList<ProjectSimpleDTO>();
		UserDTO udto = new UserDTO(u, projects);
		return udto;
	}
	
	public List<UserDTO> parseToDTO(List<User> l) {
		
		List<UserDTO> listDTO = l.stream().map(user -> {
			List<ProjectSimpleDTO> simple = parseToSimpleDTO(user.getProjects());
			return new UserDTO(user, simple);
		}).toList();
		
		return listDTO;
	}
	
	public List<MinUserInfo> parseToMinInfo(List<User> l) {
		List<MinUserInfo> listDTO = l.stream().map(user -> {
			return new MinUserInfo(user);
		}).toList();
		
		return listDTO;
	}
	
	private List<ProjectSimpleDTO> parseToSimpleDTO(List<Project> lp) {
		
		List<ProjectSimpleDTO> simpleList = lp.stream().map(project -> new ProjectSimpleDTO(project)).toList();
		return simpleList;
	}
	
	public User parseToUser(PUTUserDTO putDTO) {
		
		User u = new User(putDTO.getName(), putDTO.getRole(), 
				putDTO.getAddress(), putDTO.getEmail(), putDTO.getPassword(), putDTO.getJob());
		return u;
	}
	
	/**
	 * Función para reutilizar la misma respuesta de token requerido
	 * @return {error: "403", message: "Token is required"}
	 * */
	public ResponseEntity<?> noTokenResponse() {
		Map<String, String> body = new HashMap<String, String>();
		body.put("error", "403");
		body.put("message", "Token is required");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}
	
	public String uploadFile(MultipartFile gif) throws RuntimeException {
		try {
			File uploadedFile = convertMultiPartToFile(gif);
			Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
			boolean isDeleted = uploadedFile.delete();
			if (isDeleted) {
				System.out.println("File successfully deleted");
				return uploadResult.get("url").toString();
			} else {
				System.out.println("File doesn't exist");
				return uploadResult.get("url").toString();

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userData.findByEmailLike(username).get(0);
	}
	
	
}
