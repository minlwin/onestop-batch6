package com.jdc.shop.utils.io;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

	private Status status;
	private LocalDateTime issueAt;
	private T payload;
	
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<T>(Status.Success, LocalDateTime.now(), data);
	}

	public static <T> ApiResponse<T> validationError(T data) {
		return new ApiResponse<T>(Status.ValidationError, LocalDateTime.now(), data);
	}

	public static <T> ApiResponse<T> businessError(T data) {
		return new ApiResponse<T>(Status.BusinessError, LocalDateTime.now(), data);
	}

	public static <T> ApiResponse<T> platformError(T data) {
		return new ApiResponse<T>(Status.PlatformError, LocalDateTime.now(), data);
	}

	public static <T> ApiResponse<T> securityError(T data) {
		return new ApiResponse<T>(Status.SecurityError, LocalDateTime.now(), data);
	}

	public enum Status {
		Success,
		ValidationError,
		BusinessError,
		SecurityError,
		PlatformError
	}
	
}