package com.jdc.shop.api.employee.input;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class SaleForm {

	private int customerId;

	private BigDecimal discount;

	private List<Integer> items;

}