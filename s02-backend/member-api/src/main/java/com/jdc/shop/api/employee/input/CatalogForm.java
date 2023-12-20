package com.jdc.shop.api.employee.input;

import java.math.BigDecimal;

import com.jdc.shop.model.constants.Purity;
import com.jdc.shop.model.entity.Catalog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogForm {

	@NotBlank(message = "Please enter catalog name.")
	private String name;

	@NotNull(message = "Please select category id.")
	private Integer categoryId;

	private String description;
	
	@NotNull(message = "Please enter based price.")
	private BigDecimal basedPrice;

	@NotNull(message = "Please enter purity.")
	private Purity purity;

	@NotNull(message = "Please enter weight.")
	private BigDecimal weight;

	@NotNull(message = "Please enter lost weight.")
	private BigDecimal lostWeight;

	@NotNull(message = "Please enter gold smith fees.")
	private BigDecimal goldSmithFees;
	
	private boolean soldOut;
	
	public Catalog entity() {
		var entity = new Catalog();
		entity.setName(name);
		entity.setDescription(description);
		entity.setBasedPrice(basedPrice);
		entity.setPurity(purity);
		entity.setWeight(weight);
		entity.setLostWeight(lostWeight);
		entity.setGoldSmithFees(goldSmithFees);
		entity.setSoldOut(soldOut);
		return entity;
	}

}