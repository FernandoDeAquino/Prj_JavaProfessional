package com.dvfernandoaquino.dscommerce.controllers.handlers;

import java.time.Instant;

import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dvfernandoaquino.dscommerce.dto.CustomError;
import com.dvfernandoaquino.dscommerce.dto.ValidationError;
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

@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<CustomError> database(DataIntegrityViolationException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.BAD_REQUEST;
	CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
		
	}

@ExceptionHandler(ServiceException.class)
public ResponseEntity<CustomError> database(ServiceException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.NOT_FOUND;
	CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
		
	}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
   	String error;
	ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
   	
   	for(FieldError f: e.getBindingResult().getFieldErrors()) {
   		err.addError(f.getField(), f.getDefaultMessage());
   	}
   	
	return ResponseEntity.status(status).body(err);
		
	}
}
