package com.devil.ecomfashion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<ResultType> {

    private String message;

    private boolean success;

    private ResultType result;

    private List<ApiResponseError> errors;


    private HttpStatus httpStatus;

    public ApiResponse() {

    }

    public ApiResponse(ResultType result, String message, boolean success, ApiResponseError error) {

        this.result = result;
        this.message = message;
        this.success = success;
    }

    public ApiResponse(ResultType result, String message, boolean success, List<ApiResponseError> errors) {

        this.result = result;
        this.message = message;
        this.success = success;
        this.setErrors(errors);
    }

    public ResponseEntity<ApiResponse<ResultType>> createResponse() {

        if (httpStatus == null) httpStatus = HttpStatus.OK;

        return new ResponseEntity<>(this, httpStatus);
    }
}
