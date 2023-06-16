package com.ecommerce.backendtwo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionDetails> handleRuntimeException(RuntimeException e) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(e.getMessage(), new Date());
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NotAuthorizedException.class)
	public ResponseEntity<ExceptionDetails> handleNotAuthorizedException(RuntimeException exception) {
		return new ResponseEntity<ExceptionDetails>(
				new ExceptionDetails(exception.getMessage(), new Date()),
				HttpStatus.UNAUTHORIZED
				);
	}
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(RuntimeException exception) {
		return new ResponseEntity<ExceptionDetails>(
				new ExceptionDetails(exception.getMessage(), new Date()),
				HttpStatus.NOT_FOUND
				);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<ExceptionDetails> handleBadRequestException(RuntimeException exception) {
		return new ResponseEntity<ExceptionDetails>(
				new ExceptionDetails(exception.getMessage(), new Date()),
				HttpStatus.BAD_REQUEST
				);
	}
	
	@ExceptionHandler(value = FailedRequestException.class)
	public ResponseEntity<ExceptionDetails> handleFailedRequestException(RuntimeException exception) {
		return new ResponseEntity<ExceptionDetails>(
				new ExceptionDetails(exception.getMessage(), new Date()),
				HttpStatus.INTERNAL_SERVER_ERROR
				);
	}
}
