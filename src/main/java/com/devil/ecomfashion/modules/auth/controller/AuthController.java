package com.devil.ecomfashion.modules.auth.controller;

import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.constant.URLConstant;
import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.auth.dto.AuthDTO;
import com.devil.ecomfashion.modules.auth.model.AuthResponse;
import com.devil.ecomfashion.modules.auth.service.AuthService;
import com.devil.ecomfashion.modules.user.dto.UserDTO;
import com.devil.ecomfashion.modules.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = URLConstant.STRIKE)
@RestController
@RequestMapping(URLConstant.AUTH_BASE)
@RequiredArgsConstructor
@Validated
@Tag(name = Message.AUTH_CONTROLLER_TAG, description = Message.AUTH_CONTROLLER_DESCRIPTION)
public class AuthController {

    private final AuthService authService;

    @PostMapping(URLConstant.USER_REGISTER)
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody UserDTO userDTO) {
        ApiResponse<User> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(authService.register(userDTO));

        return apiResponseModel.createResponse();
    }

    @PostMapping(URLConstant.USER_LOGIN)
    public ResponseEntity<ApiResponse<AuthResponse>> authenticate(@Valid @RequestBody AuthDTO authDTO) {

        ApiResponse<AuthResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(authService.authenticate(authDTO));

        return apiResponseModel.createResponse();
    }

    @GetMapping(URLConstant.USER_REFRESH_TOKEN)
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(HttpServletRequest request) throws IOException {

        ApiResponse<AuthResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(authService.refreshToken(request));

        return apiResponseModel.createResponse();
    }

    @GetMapping(URLConstant.USER_LOGOUT)
    public void logout() {
    }
}
