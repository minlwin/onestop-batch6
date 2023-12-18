package com.jdc.shop.api.anonymous.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

	@NotBlank(message = "Please enter login id.")
	private String username;

	@NotBlank(message = "Please enter password.")
	private String password;
	
	public UsernamePasswordAuthenticationToken authentication() {
		return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
	}

}