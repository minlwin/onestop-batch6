package com.jdc.shop.api.employee.input;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.model.constants.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberForm {

	@NotBlank(message = "Please enter customer name.")
	private String name;

	private MultipartFile profileImage;

	@NotBlank(message = "Please enter phone number.")
	private String phone;

	private String email;

	@NotNull(message = "Please enter date of birth.")
	private LocalDate dob;

	private Gender gender;

	private String address;

	private int townshipId;

}