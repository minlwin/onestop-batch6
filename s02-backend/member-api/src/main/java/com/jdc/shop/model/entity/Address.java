package com.jdc.shop.model.entity;

import lombok.Data;

@Data
public class Address {

	private int id;

	private Member member;

	private Township township;

	private String address;

}