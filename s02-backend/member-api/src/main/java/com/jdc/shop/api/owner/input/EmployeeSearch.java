package com.jdc.shop.api.owner.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.entity.Employee;
import com.jdc.shop.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class EmployeeSearch {

	private String name;
	private String phone;
	private LocalDate from;
	private LocalDate to;
	
	public Predicate where(CriteriaBuilder cb, Root<Employee> root) {
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			var predicate = cb.like(cb.lower(root.get(Employee_.name)), name.toLowerCase().concat("%"));
			predicates.add(predicate);
		}
		
		if(StringUtils.hasLength(phone)) {
			var predicate = cb.like(cb.lower(root.get(Employee_.phone)), phone.toLowerCase().concat("%"));
			predicates.add(predicate);
		}
		
		if(null != from) {
			var predicate = cb.greaterThanOrEqualTo(root.get(Employee_.assignAt), from);
			predicates.add(predicate);
		}
		
		if(null != to) {
			var predicate = cb.lessThanOrEqualTo(root.get(Employee_.assignAt), to);
			predicates.add(predicate);
		}

		return cb.and(predicates.toArray(i -> new Predicate[i]));
	}

}