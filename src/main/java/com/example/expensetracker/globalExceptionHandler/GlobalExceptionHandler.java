package com.example.expensetracker.globalExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(ExpenseNotFoundException.class)
	    public ResponseEntity<String> handleExpenseNotFound(ExpenseNotFoundException ex) {

	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(ex.getMessage());
	    }
	    
	   // import org.springframework.web.bind.MethodArgumentNotValidException;

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidationError(MethodArgumentNotValidException ex) {

	        String errorMsg = ex.getBindingResult()
	                            .getFieldError()
	                            .getDefaultMessage();

	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(errorMsg);
	    }


}
