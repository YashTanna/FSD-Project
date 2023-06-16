package com.ecommerce.backendthree.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(ConstraintViolationException e) {
		log.error(String.valueOf(e));
		Map<String, String> errors = new HashMap<>();
		e.getConstraintViolations().forEach(x -> {
			errors.put(x.getPropertyPath().toString().split("\\.", 2)[1], x.getMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ExceptionDetails> handleUnauthorizedException(UnauthorizedException e) {
		log.error(String.valueOf(e));
		ExceptionDetails exceptionDetails = new ExceptionDetails(e.getMessage(), new Date());
		return new ResponseEntity<>(exceptionDetails, HttpStatus.UNAUTHORIZED);
	}

}
