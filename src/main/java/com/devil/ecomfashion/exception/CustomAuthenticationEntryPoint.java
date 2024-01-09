package com.devil.ecomfashion.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);

        //my util class for creating json strings
        try {
            JSONObject resJsonData = new JSONObject();
            resJsonData.put("timestamp", Long.valueOf(System.currentTimeMillis()));
            resJsonData.put("status", 403);
            resJsonData.put("message", "Access denied");
            res.getWriter().write(resJsonData.toString());
        } catch (JSONException e) {
            res.getWriter().write("message:Access denied");
        }
    }
}