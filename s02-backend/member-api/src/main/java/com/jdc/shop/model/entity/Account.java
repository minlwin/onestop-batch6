package com.jdc.shop.model.entity;

import com.jdc.shop.model.constants.Role;

import lombok.Data;

@Data
public class Account {

	public String loginId;

	public String password;

	public Role role;

}