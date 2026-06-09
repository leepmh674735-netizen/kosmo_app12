package com.witnter.app.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	private JwtTokenManager jwtTokenManager;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security
				.csrf(c -> c.disable())
				.cors(c -> c.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(a -> a
						.anyRequest().permitAll()
				)
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager));
		
		return security.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		 
		configuration.setAllowedOriginPatterns(
				Arrays.asList(
						"http://ec2Ip",
						"http://ec2Ip:80",
						"http://localhost:3000",
						"http://localhost:5173",
						"http://192.168.0.90:5173"
				)
		);
		
		configuration.setAllowedMethods(Arrays.asList(
				"GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		
		configuration.setAllowedHeaders(Arrays.asList(
				"Authorization", "Content-Type", "Cache-Control"));
		
		configuration.setExposedHeaders(Arrays.asList(
				"Authorization"));
		 
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}

}