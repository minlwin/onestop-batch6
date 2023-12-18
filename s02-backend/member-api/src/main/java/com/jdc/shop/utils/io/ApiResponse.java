package com.jdc.shop.utils.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

	private Status status;

	private T payload;
	
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<T>(Status.Success, data);
	}

	public static <T> ApiResponse<T> validationError(T data) {
		return new ApiResponse<T>(Status.ValidationError, data);
	}

	public static <T> ApiResponse<T> businessError(T data) {
		return new ApiResponse<T>(Status.BusinessError, data);
	}

	public static <T> ApiResponse<T> platformError(T data) {
		return new ApiResponse<T>(Status.PlatformError, data);
	}

	public enum Status {
		Success,
		ValidationError,
		BusinessError,
		SecurityError,
		PlatformError
	}
	
}