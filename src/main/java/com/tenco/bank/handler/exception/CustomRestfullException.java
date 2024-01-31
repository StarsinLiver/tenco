package com.tenco.bank.handler.exception;

import org.springframework.http.HttpStatus;

public class CustomRestfullException extends RuntimeException {
	
	private HttpStatus httpStatus;
	
	public CustomRestfullException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

}
