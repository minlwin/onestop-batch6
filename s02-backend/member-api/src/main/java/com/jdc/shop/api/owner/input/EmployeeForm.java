package com.jdc.shop.api.owner.input;

import java.time.LocalDate;
import java.util.function.Function;

import com.jdc.shop.model.constants.Role;
import com.jdc.shop.model.entity.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeForm {
	
	private String loginId;

	@NotBlank(message = "Please enter employee name.")
	private String name;

	@NotBlank(message = "Please enter phone number.")
	private String phone;

	@NotBlank(message = "Please enter email address.")
	private String email;

	@NotNull(message = "Please select role.")
	private Role role;

	@NotNull(message = "Please enter assign date.")
	private LocalDate assignAt;

	private LocalDate retiredAt;
	
	public Employee entity(Function<String, String> passwordFunc) {
		var entity = new Employee();
		entity.setName(name);
		entity.setPhone(phone);
		entity.setEmail(email);
		entity.setAssignAt(assignAt);
		entity.setRetiredAt(retiredAt);
		var account = entity.getAccount();
		account.setLoginId(loginId);
		account.setRole(role);
		account.setPassword(passwordFunc.apply(email));
		return entity;
	}

}