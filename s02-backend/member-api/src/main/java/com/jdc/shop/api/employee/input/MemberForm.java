package com.jdc.shop.api.employee.input;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.model.constants.Gender;

import lombok.Data;

@Data
public class MemberForm {

	private String name;

	private MultipartFile profileImage;

	private String phone;

	private String email;

	private LocalDate dob;

	private Gender gender;

	private String address;

	private int townshipId;

}