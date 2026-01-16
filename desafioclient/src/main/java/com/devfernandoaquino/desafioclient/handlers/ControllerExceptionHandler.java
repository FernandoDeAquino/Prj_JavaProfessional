package com.devfernandoaquino.desafioclient.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devfernandoaquino.desafioclient.dto.CustomError;
import com.devfernandoaquino.desafioclient.dto.ValidationError;
import com.devfernandoaquino.desafioclient.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatusCode status = HttpStatusCode.valueOf(422);
		String error;
		ValidationError err = new ValidationError(Instant.now(), status.value(),"Dados Inválidos" , request.getRequestURI());
		
		for(FieldError f: e.getBindingResult().getFieldErrors()) {
	   		err.addError(f.getField(), f.getDefaultMessage());
	   	}
	   	
		return ResponseEntity.status(status).body(err);
					
	}
	
	
}
