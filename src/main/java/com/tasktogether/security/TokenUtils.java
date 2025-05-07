package com.tasktogether.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	// CREAMOS LA SEMILLA
	private final static String ACCESS_TOKEN_SECRET = "cacadelavaca1cacadelavaca2cacadelavaca3cacadelavaca4";

	// TIEMPO EN milisegundos 4 MINUTOS
	// SOLO RECOMENDABLE EN UN PRINCIPIO MIENTRAS DESARROLLAMOS
	private final static Long ACCESS_TOKEN_LIFE_TIME = (long) (60 * 4 * 1000);

	public static String generateToken(String name, String email, String role) {
		Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_LIFE_TIME);
		Map<String, Object> payload = new HashMap<>();
		payload.put("email", email);
		payload.put("role", role);
		payload.put("name", name);

		String token = Jwts.builder().subject(name).issuedAt(expirationDate).claims(payload)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())).compact();
		return "Bearer " + token;
	}

	public static UsernamePasswordAuthenticationToken decodeToken(String token) {
		if (!token.startsWith("Bearer ")) {
			throw new MalformedJwtException("Formato no encontrado");
		}
		token = token.substring(7);
		Claims claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())).build()
				.parseSignedClaims(token).getPayload();
		String username = claims.getSubject(); // claims.get("user")
		String role = (String) claims.get("role");
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return new UsernamePasswordAuthenticationToken(username, null, authorities);
	}

}
