package com.jdc.shop.api.employee.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.shop.model.constants.Gender;
import com.jdc.shop.model.constants.Role;
import com.jdc.shop.model.entity.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberForm {
	
	private String loginId;

	@NotBlank(message = "Please enter customer name.")
	private String name;

	@NotBlank(message = "Please enter phone number.")
	private String phone;

	private String email;

	@NotNull(message = "Please enter date of birth.")
	private LocalDate dob;

	private Gender gender;

	private String address;

	private int townshipId;

	public Member entity(PasswordEncoder passwordEncoder) {
		var entity = new Member();
		entity.setName(name);
		var account = entity.getAccount();
		account.setLoginId(loginId);
		account.setRole(Role.Member);
		account.setPassword(passwordEncoder.encode(loginId));
		entity.setPhone(phone);
		entity.setEmail(email);
		entity.setDob(dob);
		entity.setGender(gender);
		entity.setRegistAt(LocalDateTime.now());
		return entity;
	}

}