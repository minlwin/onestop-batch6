package com.jdc.shop.model.entity.pk;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SalePk implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "sale_date")
	private LocalDate saleDate;

	@Column(name = "sale_seq")
	private int saleSeq;

}