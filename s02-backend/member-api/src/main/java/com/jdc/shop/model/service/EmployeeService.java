package com.jdc.shop.model.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.shop.api.owner.input.EmployeeForm;
import com.jdc.shop.api.owner.input.EmployeeSearch;
import com.jdc.shop.api.owner.output.EmployeeDto;
import com.jdc.shop.model.entity.Employee;
import com.jdc.shop.model.entity.Employee_;
import com.jdc.shop.model.repo.AccountRepo;
import com.jdc.shop.model.repo.EmployeeRepo;
import com.jdc.shop.utils.exceptions.ApiBusinessException;
import com.jdc.shop.utils.io.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo repo;
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Page<EmployeeDto> search(EmployeeSearch form, int page, int size) {
		
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Employee.class);
			// select count(e.id)
			cq.select(cb.count(root.get(Employee_.id)));
			
			cq.where(form.where(cb, root));
			return cq;
		};
		
		Function<CriteriaBuilder, CriteriaQuery<EmployeeDto>> queryFunc = cb -> {
			var cq = cb.createQuery(EmployeeDto.class);
			var root = cq.from(Employee.class);
			// select count(e.id)
			EmployeeDto.select(cq, root);
			cq.where(form.where(cb, root));
			
			cq.orderBy(cb.asc(root.get(Employee_.name)));
			
			return cq;
		};

		return repo.search(queryFunc, countFunc, page, size);
	}

	public EmployeeDto findById(int id) {
		return repo.findById(id).map(EmployeeDto::from)
				.orElseThrow(() -> new ApiBusinessException("Invalid employee id."));
	}

	@Transactional
	public DataModificationResult<Integer> create(EmployeeForm form) {
		
		if(!StringUtils.hasLength(form.getLoginId())) {
			throw new ApiBusinessException("Please enter login id.");
		}
		
		if(accountRepo.countByLoginId(form.getLoginId()) > 0) {
			throw new ApiBusinessException("Login id has been used.");
		}
		
		var entity = form.entity(passwordEncoder::encode);
		entity = repo.save(entity);
		return new DataModificationResult<Integer>(entity.getId(), "Employee has been created.");
	}

	@Transactional
	public DataModificationResult<Integer> update(int id, EmployeeForm form) {
		var entity = repo.findById(id)
				.orElseThrow(() -> new ApiBusinessException("Invalid employee id."));
		
		entity.setAssignAt(form.getAssignAt());
		entity.setRetiredAt(form.getRetiredAt());
		entity.setName(form.getName());
		entity.setPhone(form.getPhone());
		entity.setEmail(form.getEmail());
		entity.getAccount().setRole(form.getRole());
		entity.setAssignAt(form.getAssignAt());
		entity.setRetiredAt(form.getRetiredAt());
		
		return new DataModificationResult<Integer>(entity.getId(), "Employee has been updated.");
	}

}
