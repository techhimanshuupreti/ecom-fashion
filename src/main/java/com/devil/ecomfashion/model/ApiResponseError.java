package com.devil.ecomfashion.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiResponseError {

    private String title;

    private String message;
}