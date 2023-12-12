package com.jdc.shop.api.owner.input;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeSearch {

	private String name;

	private String phone;

	private LocalDate from;

	private LocalDate to;

}