package com.jdc.shop.api.employee.input;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.entity.Catalog;
import com.jdc.shop.model.entity.Catalog_;
import com.jdc.shop.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class CatalogSearch {

	private Integer categoryId;
	private String name;
	private LocalDate createFrom;
	private BigDecimal priceFrom;
	private BigDecimal priceTo;
	private Boolean soldOut;

	public Predicate where(CriteriaBuilder cb, Root<Catalog> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != categoryId && categoryId > 0) {
			var category = root.join(Catalog_.category);
			list.add(cb.equal(category.get(Category_.id), categoryId));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Catalog_.name)), name.toLowerCase().concat("%")));
		}
		
		if(null != createFrom) {
			list.add(cb.greaterThanOrEqualTo(root.get(Catalog_.createAt), createFrom.atStartOfDay()));
		}
		
		if(null != priceFrom && priceFrom.intValue() > 0) {
			list.add(cb.ge(root.get(Catalog_.salePrice), priceFrom));
		}
		
		if(null != priceTo && priceTo.intValue() > 0) {
			list.add(cb.le(root.get(Catalog_.salePrice), priceTo));
		}
		
		if(null != soldOut) {
			list.add(cb.equal(root.get(Catalog_.soldOut), soldOut));
		}
		
		return cb.and(list.toArray(i -> new Predicate[i]));
	}
}