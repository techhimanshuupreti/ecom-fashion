package com.devil.ecomfashion.projection;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Projection {
    String getName();
    Object getId();
}