package com.jdc.shop.model.repo;

import java.util.Optional;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.entity.Employee;

public interface EmployeeRepo extends BaseRepository<Employee, Integer>{

	Optional<Employee> findOneByAccountLoginId(String loginId);
}
