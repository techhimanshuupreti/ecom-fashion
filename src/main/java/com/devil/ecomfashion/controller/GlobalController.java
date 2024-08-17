package com.devil.ecomfashion.controller;

import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.exception.ExceptionOccur;
import com.devil.ecomfashion.exception.AlreadyExistException;
import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.model.ApiResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.info("handleValidationErrors : {}", ex.getMessage());
        List<ApiResponseError> errors = ex.getBindingResult().getFieldErrors().stream().map((fieldError) -> new ApiResponseError().setTitle(fieldError.getField()).setMessage(fieldError.getDefaultMessage())).collect(Collectors.toList());
        return new ResponseEntity<>(new ApiResponse<>(null, Message.ERROR_INVALID_FIELD, false, errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiResponse<?>> handleAlreadyExistException(AlreadyExistException ex) {
        log.info("handleUserAlreadyExistException : {}", ex.getMessage());
        ApiResponse<?> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(false);
        apiResponseModel.setResult(null);
        apiResponseModel.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseModel);
    }

    @ExceptionHandler(ExceptionOccur.class)
    public ResponseEntity<ApiResponse<?>> handleExceptionOccur(ExceptionOccur ex) {
        log.info("handleExceptionOccur : {}", ex.getMessage());
        ApiResponse<?> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(false);
        apiResponseModel.setResult(null);
        apiResponseModel.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseModel);
    }
}
