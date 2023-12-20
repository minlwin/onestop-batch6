package com.jdc.shop.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.shop.api.member.input.PurchaseSearch;
import com.jdc.shop.api.member.output.PurchaseDetailsDto;
import com.jdc.shop.api.member.output.PurchaseDto;

@Service
public class PurchaseService {

	public Page<PurchaseDto> search(PurchaseSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public PurchaseDetailsDto findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PurchaseDto> findByMemberId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
