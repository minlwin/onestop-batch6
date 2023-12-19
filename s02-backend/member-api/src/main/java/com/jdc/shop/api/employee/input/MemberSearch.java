package com.jdc.shop.api.employee.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.shop.model.entity.Member;
import com.jdc.shop.model.entity.Member_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class MemberSearch {

	private String name;
	private String phone;
	private LocalDate from;
	private LocalDate to;
	
	public Predicate where(CriteriaBuilder cb, Root<Member> root) {
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(name)) {
			var predicate = cb.like(cb.lower(root.get(Member_.name)), name.toLowerCase().concat("%"));
			predicates.add(predicate);
		}
		
		if(StringUtils.hasLength(phone)) {
			var predicate = cb.like(cb.lower(root.get(Member_.phone)), phone.toLowerCase().concat("%"));
			predicates.add(predicate);
		}
		
		if(null != from) {
			var predicate = cb.greaterThanOrEqualTo(root.get(Member_.registAt), from.atStartOfDay());
			predicates.add(predicate);
		}
		
		if(null != to) {
			var predicate = cb.lessThan(root.get(Member_.registAt), to.plusDays(1).atStartOfDay());
			predicates.add(predicate);
		}	
		return cb.and(predicates.toArray(i -> new Predicate[i]));
	}

}