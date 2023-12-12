package com.jdc.shop.api.owner.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GlodPriceForm {

	@NotNull(message = "Please enter effective date time.")
	private LocalDateTime businessTime;

	@NotNull(message = "Please enter sell price of 16 Pae.")
	private BigDecimal basePrice;

	@NotNull(message = "Please enter diff value of 16 Pae buy price.")
	private BigDecimal diffFor16P;

	@NotNull(message = "Please enter diff value of 15 Pae buy price.")
	private BigDecimal diffFor15P;

}