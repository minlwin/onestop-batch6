package com.jdc.shop.model.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.shop.api.owner.input.GlodPriceForm;
import com.jdc.shop.api.owner.input.GoldPriceSearch;
import com.jdc.shop.api.owner.output.GlodPriceDto;
import com.jdc.shop.utils.io.DataModificationResult;

@Service
public class GoldPriceService {

	public Page<GlodPriceDto> search(GoldPriceSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public GlodPriceDto findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<LocalDateTime> create(GlodPriceForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<LocalDateTime> update(long id, GlodPriceForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
