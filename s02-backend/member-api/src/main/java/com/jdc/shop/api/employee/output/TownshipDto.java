package com.jdc.shop.api.employee.output;

import lombok.Data;

@Data
public class TownshipDto {

	private int id;

	private String name;

	private DistrictDto district;

}