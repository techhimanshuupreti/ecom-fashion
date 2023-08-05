package com.devil.ecomfashion.modules.user.controller;


import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read','user:read')")
    public ResponseEntity<List<User>> register() {

        return ResponseEntity.ok(userService.find());
    }

}
