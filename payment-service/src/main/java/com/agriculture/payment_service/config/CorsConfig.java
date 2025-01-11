package com.agriculture.payment_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

@Bean
public CorsFilter corsFilter() {
	    CorsConfiguration config = new CorsConfiguration();
	        config.addAllowedOrigin("http://localhost:4200"); // Frontend origin
	        config.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
	        config.addAllowedHeader("*"); // Allow all headers
	        config.setAllowCredentials(true); // Allow credentials (e.g., cookies, authorization headers)

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config); // Apply CORS settings to all endpoints
	        return new CorsFilter(source);
	    }
	}


