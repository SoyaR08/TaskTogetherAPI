package com.tasktogether.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tasktogether.service.UserService;

//import com.jacaranda.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Bean
    RequestFilter requestFilter() {
        return new RequestFilter();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf(csrf-> csrf.disable())
        .cors(Customizer.withDefaults()) // Activa el soporte CORS aquí, usando la configuración que proporcione mi aplicación (o la de Spring MVC) para responder correctamente al preflight.
    	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	 
         
        
    	http.authorizeHttpRequests((requests) -> {
                requests
                .requestMatchers("/signin").permitAll()
                .requestMatchers("/users").hasAuthority("GEN_ADMIN") //Entiendo que al descifrar el token mira la autoridad del usuario
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/articulos").hasAuthority("admin")
                .anyRequest().permitAll();
    });
        
        
        http.addFilterBefore(requestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    UserService userDetailsService() {
        return new UserService();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    	return authConfig.getAuthenticationManager();
    }
    
    
    
}
