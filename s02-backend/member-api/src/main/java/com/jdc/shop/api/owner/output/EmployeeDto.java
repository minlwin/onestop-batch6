package com.jdc.shop.api.owner.output;

import java.time.LocalDate;

import com.jdc.shop.model.constants.Role;

import lombok.Data;

@Data
public class EmployeeDto {

	private int id;

	private String name;

	private String phone;

	private String email;

	private Role role;

	private LocalDate assignAt;

	private LocalDate retiredAt;

}