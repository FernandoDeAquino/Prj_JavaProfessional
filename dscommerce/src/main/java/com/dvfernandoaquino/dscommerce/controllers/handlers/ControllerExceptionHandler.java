package com.dvfernandoaquino.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dvfernandoaquino.dscommerce.dto.CustomError;
import com.dvfernandoaquino.dscommerce.services.exceptions.DatabaseException;
import com.dvfernandoaquino.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // Nesta classe, como anotation, podemos definir tratamentos globais para exceções específicas*/
public class ControllerExceptionHandler {
	
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.NOT_FOUND;
	CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
		
	}

@ExceptionHandler(DatabaseException.class)
public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.BAD_REQUEST;
	CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
		
	}
}
