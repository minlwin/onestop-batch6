package com.jdc.shop.model.entity;

import com.jdc.shop.model.constants.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	private String loginId;
	
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Role role;

}