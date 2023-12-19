package com.jdc.shop.model.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.shop.api.owner.input.EmployeeForm;
import com.jdc.shop.api.owner.input.EmployeeSearch;
import com.jdc.shop.api.owner.output.EmployeeDto;
import com.jdc.shop.utils.io.DataModificationResult;

@Service
public class EmployeeService {

	public Page<EmployeeDto> search(EmployeeSearch form, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public EmployeeDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<Integer> create(EmployeeForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataModificationResult<Integer> update(int id, EmployeeForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
