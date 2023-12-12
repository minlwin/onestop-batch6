package com.jdc.shop.api.employee.output;

import lombok.Data;

@Data
public class DistrictDto {

	private int id;

	private String name;

	private StateDto state;

}