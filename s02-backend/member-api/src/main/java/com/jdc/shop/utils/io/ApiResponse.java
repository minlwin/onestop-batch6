package com.jdc.shop.utils.io;

import lombok.Data;

@Data
public class ApiResponse<T> {

	private Status status;

	private T payload;

	public enum Status {
		Success,
		ValidationError,
		BusinessError,
		SecurityError,
		PlatformError
	}

}