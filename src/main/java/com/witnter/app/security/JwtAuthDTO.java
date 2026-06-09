package com.witnter.app.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthDTO {
	
	private String accessToken;
	private String refreshToken;
	private String username;

}
