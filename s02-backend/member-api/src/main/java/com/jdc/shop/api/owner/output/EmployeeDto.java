package com.jdc.shop.api.owner.output;

import java.time.LocalDate;

import com.jdc.shop.model.constants.Role;
import com.jdc.shop.model.entity.Account_;
import com.jdc.shop.model.entity.Employee;
import com.jdc.shop.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

	private int id;
	private String name;
	private String phone;
	private String email;
	private Role role;
	private LocalDate assignAt;
	private LocalDate retiredAt;
	
	public static EmployeeDto from(Employee entity) {
		return new EmployeeDto(entity.getId(), 
				entity.getName(), 
				entity.getPhone(), 
				entity.getEmail(), 
				entity.getAccount().getRole(), 
				entity.getAssignAt(), 
				entity.getRetiredAt());
	}
	
	public static void select(CriteriaQuery<EmployeeDto> cq, Root<Employee> root) {
		cq.multiselect(
			root.get(Employee_.id),
			root.get(Employee_.name),
			root.get(Employee_.phone),
			root.get(Employee_.email),
			root.get(Employee_.account).get(Account_.role),
			root.get(Employee_.assignAt),
			root.get(Employee_.retiredAt)
		);
	}

}