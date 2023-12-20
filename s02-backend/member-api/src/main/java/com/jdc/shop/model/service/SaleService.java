package com.jdc.shop.model.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.shop.api.employee.input.SaleForm;
import com.jdc.shop.api.employee.input.SaleSearch;
import com.jdc.shop.api.employee.output.SaleDetailsDto;
import com.jdc.shop.api.employee.output.SaleDto;
import com.jdc.shop.model.entity.pk.SalePk;
import com.jdc.shop.utils.io.DataModificationResult;

@Service
public class SaleService {

	public Page<SaleDto> search(SaleSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public SaleDetailsDto findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<SalePk> create(SaleForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<SalePk> update(String id, SaleForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
