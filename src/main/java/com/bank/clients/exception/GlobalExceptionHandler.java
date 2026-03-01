package com.bank.clients.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleNotFound(final ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", ex.getMessage()));
	}

	@ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
	public ResponseEntity<Map<String, String>> handleBadRequest(final Exception ex) {
		final String mensagem = ex instanceof MethodArgumentNotValidException methodArgumentNotValidException
				? methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage()
				: ex.getMessage();
		return ResponseEntity.badRequest().body(Map.of("erro", mensagem));
	}
}
