package com.witnter.app.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	
	@Value("${jwt.accessValidTime}")
	private Long accessValidTime;
	
	@Value("${jwt.refreshValidTime}")
	private Long refreshValidTime;
	
	@Value("${jwt.issuer}")
	private String issuer;
	
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	private SecretKey key;
	
	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String createAccessToken(Authentication authentication) {
		return this.createToken(authentication, accessValidTime);
	}
	
	public String createRefreshToken(Authentication authentication) {
		return this.createToken(authentication, refreshValidTime);
	}
		
	private String createToken(Authentication authentication, Long validTime) {
		return Jwts.builder()
				.subject(authentication.getName())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + validTime))
				.issuer(issuer)
				.signWith(key)
				.compact();
	}
		
	public Claims getAuthenticationByToken(String token) throws Exception {
		return Jwts.parser()
				.verifyWith(this.key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
}