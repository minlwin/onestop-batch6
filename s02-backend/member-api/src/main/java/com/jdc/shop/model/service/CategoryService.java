package com.jdc.shop.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.api.employee.input.CategoryForm;
import com.jdc.shop.api.employee.output.CatagoryDto;
import com.jdc.shop.model.repo.CategoryRepo;
import com.jdc.shop.utils.exceptions.ApiBusinessException;
import com.jdc.shop.utils.io.DataModificationResult;

@Service
@Transactional(readOnly = true)
public class CategoryService {
	
	@Autowired
	private CategoryRepo repo;

	public List<CatagoryDto> findAll() {
		return repo.findAll().stream().map(CatagoryDto::from).toList();
	}

	public CatagoryDto findById(int id) {
		return repo.findById(id).map(CatagoryDto::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid Id"));
	}

	@Transactional
	public DataModificationResult<Integer> create(CategoryForm form) {
		var entity = repo.save(form.entity());
		return new DataModificationResult<Integer>(entity.getId(), "Category has been created.");
	}

	@Transactional
	public DataModificationResult<Integer> update(int id, CategoryForm form) {		
		var entity = repo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("Invalid Id"));
		entity.setName(form.getName());
		return new DataModificationResult<Integer>(entity.getId(), "Category has been updated.");
	}

}
