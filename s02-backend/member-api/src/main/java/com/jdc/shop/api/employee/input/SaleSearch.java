package com.jdc.shop.api.employee.input;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SaleSearch {

	private String customerName;

	private String customerPhone;

	private LocalDate from;

	private LocalDate to;

}