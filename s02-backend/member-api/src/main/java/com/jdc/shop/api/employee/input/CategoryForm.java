package com.jdc.shop.api.employee.input;

import com.jdc.shop.model.entity.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryForm {

	@NotBlank(message = "Please enter category name.")
	private String name;

	public Category entity() {
		var entity = new Category();
		entity.setName(name);
		return entity;
	}
}