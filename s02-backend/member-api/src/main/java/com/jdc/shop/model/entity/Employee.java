package com.jdc.shop.model.entity;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Employee extends AbstractEntity {

	private int id;

	private String name;

	private Account account;

	private String phone;

	private String email;

	private LocalDate assignAt;

	private LocalDate retiredAt;

}