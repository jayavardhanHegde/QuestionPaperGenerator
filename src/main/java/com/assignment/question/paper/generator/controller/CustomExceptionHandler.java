package com.assignment.question.paper.generator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.question.paper.generator.model.ApiError;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ComponentScan
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
	List<String> errors = new ArrayList<String>();
	for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	    errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
		    + violation.getMessage());
	}

	return new ResponseEntity<Object>(new ApiError(ex.getLocalizedMessage(), "QG-APP-103"), new HttpHeaders(),
		HttpStatus.BAD_REQUEST);
    }

}