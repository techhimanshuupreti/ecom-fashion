package com.devil.ecomfashion.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiResponse<ResultType> {

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    private String message;

    private boolean success;

    private ResultType result;

    private List<ApiResponseError> errors;

    @JsonIgnore
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
