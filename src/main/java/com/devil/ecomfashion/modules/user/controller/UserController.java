package com.devil.ecomfashion.modules.user.controller;


import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.user.dto.UserDTO;
import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000/", "*"})
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
@Tag(name = "User",description = "user related api")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> find() {

        ApiResponse<List<User>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(userService.find());

        return apiResponseModel.createResponse();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<User>> findOne(@PathVariable long id) {

        ApiResponse<User> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(userService.findOne(id));

        return apiResponseModel.createResponse();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> create(@RequestBody UserDTO userDTO) {
        ApiResponse<User> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(userService.create(userDTO));

        return apiResponseModel.createResponse();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {

        ApiResponse<User> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(userService.updateUser(id,userDTO));

        return apiResponseModel.createResponse();
    }
}
