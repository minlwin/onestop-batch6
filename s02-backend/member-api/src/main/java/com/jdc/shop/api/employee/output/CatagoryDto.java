package com.jdc.shop.api.employee.output;

import com.jdc.shop.model.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatagoryDto {

	private int id;
	private String name;
	
	public static CatagoryDto from(Category entity) {
		return new CatagoryDto(entity.getId(), entity.getName());
	}
}