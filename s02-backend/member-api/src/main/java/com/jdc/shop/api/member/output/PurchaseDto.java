package com.jdc.shop.api.member.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.shop.model.entity.Employee_;
import com.jdc.shop.model.entity.Sale;
import com.jdc.shop.model.entity.Sale_;
import com.jdc.shop.model.entity.pk.SalePk;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

	private SalePk id;
	private LocalDateTime saleAt;
	private String saleBy;
	private BigDecimal salePrice;
	private BigDecimal discount;
	
	public static PurchaseDto from(Sale entity) {
		var dto = new PurchaseDto();
		dto.setId(entity.getId());
		dto.setSaleBy(entity.getSellBy().getName());
		dto.setSaleAt(entity.getSaleAt());
		dto.setSalePrice(entity.getSalePrice());
		dto.setDiscount(entity.getDiscount());
		return dto;
	}

	public static void select(CriteriaQuery<PurchaseDto> cq, Root<Sale> root) {
		cq.multiselect(
			root.get(Sale_.id),
			root.get(Sale_.saleAt),
			root.get(Sale_.sellBy).get(Employee_.name),
			root.get(Sale_.salePrice),
			root.get(Sale_.discount)
		);
	}	
}