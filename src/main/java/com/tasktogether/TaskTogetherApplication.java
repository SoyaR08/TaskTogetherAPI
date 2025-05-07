package com.tasktogether;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;

@SpringBootApplication
public class TaskTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTogetherApplication.class, args);
	}
	
	//Bean de Cloudinary
	@Bean
	Cloudinary cloudinaryConfig() {
	    Cloudinary cloudinary = null;
	    Map<String, String> config = new HashMap<>();
	    config.put("cloud_name", "dv5lyqil9"); // Pon aquí tu cloud name
	    config.put("api_key", "138928335749333");  // Tu API Key
	    config.put("api_secret", "PiglC8jcyZXWQSd4zCyqdCbubEg"); // Tu API Secret
	    cloudinary = new Cloudinary(config);
	    return cloudinary;
	}


}
