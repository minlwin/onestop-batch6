package com.jdc.shop.api.anonymous.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

	@NotBlank(message = "Please enter login id.")
	private String username;

	@NotBlank(message = "Please enter password.")
	private String password;

}