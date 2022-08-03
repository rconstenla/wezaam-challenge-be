package com.wezaam.withdrawal.exception;

import org.springframework.http.HttpStatus;

public class TransactionException extends Exception {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	public TransactionException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
