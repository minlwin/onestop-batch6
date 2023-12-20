package com.jdc.shop.model.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.model.entity.SaleSeq;
import com.jdc.shop.model.entity.pk.SalePk;
import com.jdc.shop.model.repo.SaleSeqRepo;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SaleIdGenerator {

	@Autowired
	private SaleSeqRepo repo;
	
	public SalePk generate(LocalDate today) {
		var seq = repo.findById(today)
			.orElseGet(() -> {
				var entity = new SaleSeq();
				entity.setSaleDate(today);
				return repo.save(entity);
			});
		
		return seq.next();
	}
}
