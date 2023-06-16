package com.ecommerce.backendone.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionDetails> handleRuntimeException(RuntimeException e) {
		log.error(String.valueOf(e));
		ExceptionDetails exceptionDetails = new ExceptionDetails(e.getMessage(), new Date());
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
		log.error(String.valueOf(e));
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NotAuthorizedException.class)
	public ResponseEntity<ExceptionDetails> handleNotAuthorizedException(NotAuthorizedException exception) {
		return new ResponseEntity<ExceptionDetails>(new ExceptionDetails(exception.getMessage(), new Date()),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(ResourceNotFoundException exception) {
		return new ResponseEntity<ExceptionDetails>(new ExceptionDetails(exception.getMessage(), new Date()),
				HttpStatus.NOT_FOUND);
	}
}
