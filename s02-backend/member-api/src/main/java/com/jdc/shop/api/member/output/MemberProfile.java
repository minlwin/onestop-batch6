package com.jdc.shop.api.member.output;

import java.time.LocalDate;

import com.jdc.shop.model.constants.Gender;

import lombok.Data;

@Data
public class MemberProfile {

	private int id;

	private String name;

	private String profileImage;

	private String phone;

	private String email;

	private LocalDate dob;

	private Gender gender;

	private String address;

	private String township;

	private String state;

	private LocalDate registrationDate;

}