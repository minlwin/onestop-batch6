package com.jdc.shop.model.entity.pk;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

import com.jdc.shop.utils.exceptions.ApiBusinessException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalePk implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd");

	@Column(name = "sale_date")
	private LocalDate saleDate;

	@Column(name = "sale_seq")
	private int saleSeq;
	
	public String getInvoiceNumber() {
		return "INV-%s%04d".formatted(saleDate.format(DF), saleSeq);
	}

	public static SalePk from(String invoiceNumber) {
		var id = invoiceNumber.replaceAll("INV-", "");
		
		if(StringUtils.hasLength(id) && id.length() > 8) {
			var key = id.substring(0, 8);
			var seq = Integer.valueOf(id.substring(8));
			return new SalePk(LocalDate.parse(key, DF), seq);
		}
		
		throw new ApiBusinessException("Invalid invoice number");
	}
}