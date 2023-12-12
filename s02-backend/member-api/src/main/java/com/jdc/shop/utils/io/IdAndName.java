package com.jdc.shop.utils.io;

import lombok.Data;

@Data
public class IdAndName<T> {

	private T id;

	private String name;

}