package com.jdc.shop.api.employee.input;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaleForm {

	@NotNull(message = "Please select customer")
	private Integer customerId;

	private BigDecimal discount;

	@NotEmpty(message = "Please select items.")
	private List<Integer> items;

}