package com.devil.ecomfashion.exception;

import com.devil.ecomfashion.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

public class CustomAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        res.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        res.setStatus(HttpStatus.UNAUTHORIZED.value());

        //my util class for creating json strings
        try {
            JSONObject resJsonData = new JSONObject();
            resJsonData.put("timestamp", Long.valueOf(System.currentTimeMillis()));
            resJsonData.put("status", HttpStatus.UNAUTHORIZED.value());
            resJsonData.put("message", HttpStatus.UNAUTHORIZED.name());
            resJsonData.put("value", authException.getMessage());
            res.getWriter().write(resJsonData.toString());
        } catch (JSONException e) {
            res.getWriter().write(ObjectUtils.getDisplayString(new ApiResponse<String>().setMessage(authException.getMessage())));
        }
    }


}