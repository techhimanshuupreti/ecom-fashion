package com.devil.ecomfashion.modules.auth.controller;

import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.auth.dto.AuthDTO;
import com.devil.ecomfashion.modules.auth.model.AuthResponse;
import com.devil.ecomfashion.modules.auth.service.AuthService;
import com.devil.ecomfashion.modules.user.UserDTO;
import com.devil.ecomfashion.modules.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody UserDTO userDTO) {

        ApiResponse<User> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(authService.register(userDTO));

        return apiResponseModel.createResponse();
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> authenticate(@RequestBody AuthDTO authDTO) {

        ApiResponse<AuthResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(authService.authenticate(authDTO));

        return apiResponseModel.createResponse();
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        authService.refreshToken(request, response);
    }

    @GetMapping("/logout")
    public void logout() {
    }
}
