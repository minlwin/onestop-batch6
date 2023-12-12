package com.jdc.shop.api.anonymous.output;

import com.jdc.shop.model.constants.Role;

import lombok.Data;

@Data
public class LoginResult {

	private int id;

	private String name;

	private Role role;

	private String token;

}