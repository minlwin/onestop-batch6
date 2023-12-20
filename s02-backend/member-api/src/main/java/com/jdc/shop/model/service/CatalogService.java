package com.jdc.shop.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.api.anonymous.output.CatalogDetailsDto;
import com.jdc.shop.api.anonymous.output.CatalogDto;
import com.jdc.shop.api.employee.input.CatalogForm;
import com.jdc.shop.api.employee.input.CatalogSearch;
import com.jdc.shop.model.repo.CatalogRepo;
import com.jdc.shop.model.repo.CategoryRepo;
import com.jdc.shop.utils.io.DataModificationResult;

@Service
public class CatalogService {
	
	@Autowired
	private CatalogRepo catalogRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	public Page<CatalogDto> search(CatalogSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public CatalogDetailsDto findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<Integer> create(CatalogForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<Integer> update(int id, CatalogForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<Integer> uploadPhoto(int id, List<MultipartFile> files) {
		// TODO Auto-generated method stub
		return null;
	}

}
