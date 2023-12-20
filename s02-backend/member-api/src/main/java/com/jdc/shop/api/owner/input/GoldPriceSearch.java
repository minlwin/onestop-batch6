package com.jdc.shop.api.owner.input;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jdc.shop.model.entity.GoldPrice;
import com.jdc.shop.model.entity.GoldPrice.Status;
import com.jdc.shop.model.entity.GoldPrice_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class GoldPriceSearch {

	private LocalDate from;
	private LocalDate to;
	private Status status;
	
	public Predicate where(CriteriaBuilder cb, Root<GoldPrice> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(GoldPrice_.businessTime), from.atStartOfDay()));
		}
		
		if(null != to) {
			list.add(cb.lessThan(root.get(GoldPrice_.businessTime), from.plusDays(1).atStartOfDay()));
		}

		if(null != status) {
			list.add(cb.lessThan(root.get(GoldPrice_.status), status));
		}

		return cb.and(list.toArray(a -> new Predicate[a]));
	}

}