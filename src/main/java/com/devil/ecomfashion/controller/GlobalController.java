package com.devil.ecomfashion.controller;

import com.devil.ecomfashion.exception.JwtExceptionHandler;
import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.model.ApiResponseError;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMissingServletRequestPart(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ApiResponseError> errors = ex.getBindingResult().getFieldErrors().stream().map((fieldError) -> new ApiResponseError().setTitle(fieldError.getField()).setMessage(fieldError.getDefaultMessage())).toList();
//
        return new ResponseEntity<>(new ApiResponse<>(null, "invalid field ", true, errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleAsyncRequestTimeoutException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleErrorResponseException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ApiResponse<String>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {

        ApiResponse<String> errorDetails = new ApiResponse<>();
        errorDetails.setResult(null);
        errorDetails.setSuccess(false);
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setHttpStatus(HttpStatus.FORBIDDEN);

        return errorDetails.createResponse();
    }


    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthenticationException(Exception ex) {

        ApiResponse<String> errorDetails = new ApiResponse<>();
        errorDetails.setResult(null);
        errorDetails.setSuccess(false);
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setHttpStatus(HttpStatus.UNAUTHORIZED);

        return errorDetails.createResponse();
    }

    @ResponseBody
    @ExceptionHandler({JwtException.class, ExpiredJwtException.class, ClaimJwtException.class, JwtExceptionHandler.class})
    public final ResponseEntity<ApiResponse<String>> handleJwtException(JwtException ex, WebRequest request) {

        ApiResponse<String> errorDetails = new ApiResponse<>();
        errorDetails.setResult(null);
        errorDetails.setSuccess(false);
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setHttpStatus(HttpStatus.INSUFFICIENT_STORAGE);

        return errorDetails.createResponse();
    }

}
