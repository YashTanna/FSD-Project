package com.ecommerce.backendtwo.exception;

public class FailedRequestException extends RuntimeException {
	
    public FailedRequestException() {
		super();
	}

	public FailedRequestException(String message) {
        super(message);
    }
}
