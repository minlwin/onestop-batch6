package com.jdc.shop.model.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "EMPLOYEE")
@SequenceGenerator(name = "EMPLOYEE_SEQ", allocationSize = 1)
public class Employee extends AbstractEntity {

	@Id
	@GeneratedValue(generator = "EMPLOYEE_SEQ")
	private int id;

	@Column(nullable = false)
	private String name;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Account account = new Account();

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private LocalDate assignAt;

	private LocalDate retiredAt;

	public void setName(String name) {
		this.name = name;
		account.setName(name);
	}
}