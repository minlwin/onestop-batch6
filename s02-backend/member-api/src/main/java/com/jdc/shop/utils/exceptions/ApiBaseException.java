package com.jdc.shop.utils.exceptions;

import java.util.List;

public abstract class ApiBaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private List<String> messages;
	
	public ApiBaseException(List<String> messages) {
		super();
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

}
