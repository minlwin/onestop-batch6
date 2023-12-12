package com.jdc.shop.api.employee.input;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberSearch {

	private String name;

	private String phone;

	private LocalDate from;

	private LocalDate to;

}