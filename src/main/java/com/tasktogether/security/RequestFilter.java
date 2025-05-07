package com.tasktogether.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestFilter extends OncePerRequestFilter{


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		
		if (token != null) {
			
			try {
				UsernamePasswordAuthenticationToken authentication = TokenUtils.decodeToken(token);
				//authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			
			} catch (ExpiredJwtException e) {
				throw new RuntimeException("Token expirado");
			
			} catch (MalformedJwtException e) {
				throw new MalformedJwtException("Token mal formado");
			
			} catch (Exception e) {
				throw new RuntimeException("Error en autenticación: " + e.getMessage(), e);
			}

		}
		//Esta mierda tiene que estar fuera del if porque me petaba sino había token
		filterChain.doFilter(request, response);
	}
	
}
