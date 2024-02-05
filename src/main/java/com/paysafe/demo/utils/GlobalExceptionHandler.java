package com.paysafe.demo.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ErrorMapper errorMapper;

    public GlobalExceptionHandler(
        ErrorMapper errorMapper
    ) {
        this.errorMapper = errorMapper;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleException(Throwable e) {
        if (e instanceof ClientAbortException) {
            return null; //socket is closed, cannot return any response
        } else {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        return new ResponseEntity<>(this.errorMapper.createErrorMap(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder strBuilder = new StringBuilder();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            try {
                fieldName = ((FieldError) error).getField();

            } catch (ClassCastException ex) {
                fieldName = error.getObjectName();
            }
            String message = error.getDefaultMessage();
            strBuilder.append(String.format("%s: %s\n", fieldName, message));
        });

        return new ResponseEntity<>(errorMapper.createErrorMap(strBuilder.substring(0, strBuilder.length()-1)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();

        constraintViolations.forEach((constraintViolation) -> {
            String fieldName = String.format("%s", constraintViolation.getPropertyPath());
            String message = constraintViolation.getMessage();
            strBuilder.append(String.format("%s: %s\n", fieldName, message));
        });

        return new ResponseEntity<>(errorMapper.createErrorMap(strBuilder.substring(0, strBuilder.length()-1)), HttpStatus.BAD_REQUEST);
    }
}