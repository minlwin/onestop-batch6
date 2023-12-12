package com.jdc.shop.api.employee.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryForm {

	@NotBlank(message = "Please enter category name.")
	private String name;

}