package com.jdc.shop.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.shop.model.constants.Gender;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Member extends AbstractEntity {

	private int id;

	private String name;

	private Account account;

	private String phone;

	private String email;

	private Gender gender;

	private LocalDate dob;

	private String profileImage;

	private LocalDateTime registAt;

	private Address address;

}