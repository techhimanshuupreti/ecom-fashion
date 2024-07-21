package com.devil.ecomfashion.exception;

import com.devil.ecomfashion.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDeniedException) throws IOException {
        res.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
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
            res.getWriter().write(ObjectUtils.toString(new ApiResponse<String>().setMessage(accessDeniedException.getMessage())));
        }
    }
}
