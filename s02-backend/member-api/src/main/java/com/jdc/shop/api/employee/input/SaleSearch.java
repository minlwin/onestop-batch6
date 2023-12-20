package com.jdc.shop.api.employee.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.entity.Member_;
import com.jdc.shop.model.entity.Sale;
import com.jdc.shop.model.entity.Sale_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class SaleSearch {

	private String customerName;
	private String customerPhone;
	private LocalDate from;
	private LocalDate to;
	
	public Predicate where(CriteriaBuilder cb, Root<Sale> root) {
		var list = new ArrayList<Predicate>();
		
		var customer = root.join(Sale_.customer);
		
		if(StringUtils.hasLength(customerName)) {
			list.add(cb.like(cb.lower(customer.get(Member_.name)), customerName.toLowerCase().concat("%")));
		}
		
		if(StringUtils.hasLength(customerPhone)) {
			list.add(cb.like(cb.lower(customer.get(Member_.phone)), customerPhone.toLowerCase().concat("%")));
		}
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(Sale_.saleAt), from.atStartOfDay()));
		}
		
		if(null != to) {
			list.add(cb.lessThan(root.get(Sale_.saleAt), to.plusDays(1).atStartOfDay()));
		}
		
		return cb.and(list.toArray(i -> new Predicate[i]));
	}

}