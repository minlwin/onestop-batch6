package com.jdc.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESS")
public class Address {

	@Id
	private int id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Township township;

	private String address;

}