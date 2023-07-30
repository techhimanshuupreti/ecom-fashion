package com.devil.ecomfashion.modules.auth.controller;

import com.devil.ecomfashion.modules.auth.model.AuthResponse;
import com.devil.ecomfashion.modules.auth.service.AuthService;
import com.devil.ecomfashion.modules.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDTO userDTO){

        return ResponseEntity.ok(authService.register(userDTO));
    }

    

}
