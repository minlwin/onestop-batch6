package com.jdc.shop.api.owner.input;

import java.time.LocalDate;

import com.jdc.shop.model.constants.Role;

import lombok.Data;

@Data
public class EmployeeForm {

	private String name;

	private String phone;

	private String email;

	private Role role;

	private LocalDate assignAt;

	private LocalDate retiredAt;

}