package com.jdc.shop.model.entity;

import java.time.LocalDate;

import com.jdc.shop.model.entity.pk.SalePk;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SALE_SEQ")
public class SaleSeq {
	
	@Id
	@Column(name = "sale_date")
	private LocalDate saleDate;

	@Column(name = "sale_seq")
	private int saleSeq;
	
	public SalePk next() {
		var pk = new SalePk();
		pk.setSaleDate(saleDate);
		pk.setSaleSeq(++ saleSeq);
		return pk;
	}

}
