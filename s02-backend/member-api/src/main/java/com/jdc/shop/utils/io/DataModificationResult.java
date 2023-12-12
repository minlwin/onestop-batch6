package com.jdc.shop.utils.io;

import lombok.Data;

@Data
public class DataModificationResult<T> {

	private T id;

	private String message;

}