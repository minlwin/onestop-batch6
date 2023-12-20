package com.jdc.shop.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.shop.model.constants.Gender;

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
@Table(name = "MEMBER")
@SequenceGenerator(name = "MEMBER_SEQ", allocationSize = 1)
public class Member extends AbstractEntity {

	@Id
	@GeneratedValue(generator = "MEMBER_SEQ")
	private int id;

	@Column(nullable = false)
	private String name;

	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Account account = new Account();
	
	@Column(nullable = false)
	private String phone;

	private String email;

	private Gender gender;

	@Column(nullable = false)
	private LocalDate dob;

	private String profileImage;

	private LocalDateTime registAt;
	
	@OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Address address;
	
	public void setAddress(Address address) {
		this.address = address;
		address.setMember(this);
	}
	
	public void setName(String name) {
		this.name = name;
		this.account.setName(name);
	}

}