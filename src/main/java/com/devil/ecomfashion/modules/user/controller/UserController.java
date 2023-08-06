package com.devil.ecomfashion.modules.user.controller;


import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> find() {

        return ResponseEntity.ok(userService.find());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findOne(@PathVariable long id) {

        return ResponseEntity.ok(userService.findOne(id));
    }

}
