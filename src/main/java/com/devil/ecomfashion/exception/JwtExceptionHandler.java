package com.devil.ecomfashion.exception;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;

public class JwtExceptionHandler extends ExpiredJwtException  {

    public JwtExceptionHandler(Header header, Claims claims, String message) {
        super(header, claims, message);
    }

    public JwtExceptionHandler(Header header, Claims claims, String message, Throwable cause) {
        super(header, claims, message, cause);
    }
}
