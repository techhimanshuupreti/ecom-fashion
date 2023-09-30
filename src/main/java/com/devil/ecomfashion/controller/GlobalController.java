package com.devil.ecomfashion.controller;

import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.model.ApiResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.SignatureException;
import java.util.List;

@RestControllerAdvice
public class GlobalController{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ApiResponseError> errors = ex.getBindingResult().getFieldErrors().stream().map((fieldError) -> new ApiResponseError().setTitle(fieldError.getField()).setMessage(fieldError.getDefaultMessage())).toList();

        return new ResponseEntity<>(new ApiResponse<>(null, "invalid field ", true, errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
