package com.devil.ecomfashion.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(401);

        //my util class for creating json strings
        try {
            JSONObject resJsonData = new JSONObject();
            resJsonData.put("timestamp", Long.valueOf(System.currentTimeMillis()));
            resJsonData.put("status", 401);
            resJsonData.put("message", "Unauthorized");
            resJsonData.put("value", authException.getMessage());
            res.getWriter().write(resJsonData.toString());
        } catch (JSONException e) {
            res.getWriter().write("message:Unauthorized");
        }
    }

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);

        //my util class for creating json strings
        try {
            JSONObject resJsonData = new JSONObject();
            resJsonData.put("timestamp", Long.valueOf(System.currentTimeMillis()));
            resJsonData.put("status", 403);
            resJsonData.put("message", "Access denied");
            resJsonData.put("value", accessDeniedException.getMessage());
            res.getWriter().write(resJsonData.toString());
        } catch (JSONException e) {
            res.getWriter().write("message:Access denied");
        }
    }
}