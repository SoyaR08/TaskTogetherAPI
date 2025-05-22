package com.tasktogether.libraries;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class Defaultresponse {

	/**
	 * Este método recibe un mensaje y devuelve una respuesta 403 con ese mensaje como descripción
	 * @param message El mensaje de error que desate la respuesta 403
	 * @return respuesta HTTP 403
	 */
	public ResponseEntity<?> forbiddenResponse(String message) {
		Map<String, String> body = new HashMap<String, String>();
		body.put("error", "403");
		body.put("message", message);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	
	/**
	 * Este método recibe un mensaje y devuelve una respuesta 401 con ese mensaje como descripción
	 * @param message El mensaje de error que desate la respuesta 401
	 * @return respuesta HTTP 401
	 */
	public ResponseEntity<?> unauthorizedResponse(String message) {
		Map<String, String> body = new HashMap<String, String>();
		body.put("error", "401");
		body.put("message", message);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}
	
	/**
	 * Este método recibe un mensaje y devuelve una respuesta 500 con ese mensaje como descripción
	 * @param message El mensaje de error que desate la respuesta 500
	 * @return respuesta HTTP 500
	 */
	public ResponseEntity<?> servererrorResponse(String message) {
		Map<String, String> body = new HashMap<String, String>();
		body.put("error", "500");
		body.put("message", message);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}
	
	/**
	 * Este método recibe un mensaje y devuelve una respuesta 404 con ese mensaje como descripción
	 * @param message El mensaje de error que desate la respuesta 404
	 * @return respuesta HTTP 404
	 */
	public ResponseEntity<?> notfoundResponse(String message) {
		Map<String, String> body = new HashMap<String, String>();
		body.put("error", "404");
		body.put("message", message);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	
}
