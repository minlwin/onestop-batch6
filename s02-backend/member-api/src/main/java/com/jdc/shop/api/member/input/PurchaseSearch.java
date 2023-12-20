package com.jdc.shop.api.member.input;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jdc.shop.model.entity.Sale;
import com.jdc.shop.model.entity.Sale_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class PurchaseSearch {

	private LocalDate from;
	private LocalDate to;

	public Predicate where(CriteriaBuilder cb, Root<Sale> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(Sale_.saleAt), from.atStartOfDay()));
		}
		
		if(null != to) {
			list.add(cb.lessThan(root.get(Sale_.saleAt), to.plusDays(1).atStartOfDay()));
		}
		
		return cb.and(list.toArray(i -> new Predicate[i]));
	}
}