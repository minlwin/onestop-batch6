package com.jdc.shop.api.employee.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jdc.shop.model.entity.Address_;
import com.jdc.shop.model.entity.Employee_;
import com.jdc.shop.model.entity.Member_;
import com.jdc.shop.model.entity.Sale;
import com.jdc.shop.model.entity.Sale_;
import com.jdc.shop.model.entity.Township_;
import com.jdc.shop.model.entity.pk.SalePk;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {

	private SalePk id;
	private int memberId;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String memberAddress;
	private String memberTownship;
	private String saleBy;
	private LocalDateTime saleAt;
	private BigDecimal salePrice;
	private BigDecimal discount;
	
	public static SaleDto from(Sale entity) {
		var dto = new SaleDto();
		dto.setId(entity.getId());
		dto.setMemberId(entity.getCustomer().getId());
		dto.setMemberName(entity.getCustomer().getName());
		dto.setMemberPhone(entity.getCustomer().getPhone());
		dto.setMemberEmail(entity.getCustomer().getEmail());
		dto.setMemberAddress(entity.getCustomer().getAddress().getAddress());
		dto.setMemberTownship(entity.getCustomer().getAddress().getTownship().getName());
		dto.setSaleBy(entity.getSellBy().getName());
		dto.setSaleAt(entity.getSaleAt());
		dto.setSalePrice(entity.getSalePrice());
		dto.setDiscount(entity.getDiscount());
		return dto;
	}
	
	public static void select(CriteriaQuery<SaleDto> cq, Root<Sale> root) {
		var member = root.join(Sale_.customer);
		cq.multiselect(
			root.get(Sale_.id),
			member.get(Member_.id),
			member.get(Member_.name),
			member.get(Member_.phone),
			member.get(Member_.email),
			member.get(Member_.address).get(Address_.address),
			member.get(Member_.address).get(Address_.township).get(Township_.name),
			root.get(Sale_.sellBy).get(Employee_.name),
			root.get(Sale_.saleAt),
			root.get(Sale_.salePrice),
			root.get(Sale_.discount)
		);
	}

}