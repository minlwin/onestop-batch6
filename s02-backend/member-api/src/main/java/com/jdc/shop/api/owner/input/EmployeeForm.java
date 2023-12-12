package com.jdc.shop.api.owner.input;

import java.time.LocalDate;

import com.jdc.shop.model.constants.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeForm {

	@NotBlank(message = "PLease enter employee name.")
	private String name;

	@NotBlank(message = "PLease enter phone number.")
	private String phone;

	@NotBlank(message = "PLease enter email address.")
	private String email;

	@NotNull(message = "Please select role.")
	private Role role;

	@NotNull(message = "Please enter assign date.")
	private LocalDate assignAt;

	private LocalDate retiredAt;

}