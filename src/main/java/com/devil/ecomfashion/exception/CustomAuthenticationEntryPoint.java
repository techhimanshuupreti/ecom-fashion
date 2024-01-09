package com.devil.ecomfashion.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.sql.Timestamp;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
//        JSONOBjec
//        res.getWriter().write(new JsonObj //my util class for creating json strings
//                .put("timestamp", new Timestamp(System.currentTimeMillis()))
//                .put("status", 403)
//                .put("message", "Access denied")
//                .build());
    }
}