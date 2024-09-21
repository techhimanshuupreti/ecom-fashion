package com.devil.ecomfashion.modules.user.controller;


import com.devil.ecomfashion.constant.Constants;
import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.constant.URLConstant;
import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.user.dto.UserDTO;
import com.devil.ecomfashion.modules.user.dto.UserResponse;
import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.service.UserService;
import com.devil.ecomfashion.utils.PageableResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = URLConstant.STRIKE)
@RestController
@RequestMapping(URLConstant.USER_BASE)
@RequiredArgsConstructor
@Validated
@Tag(name = Message.USER_CONTROLLER_TAG, description = Message.USER_DESCRIPTION)
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageableResponse<UserResponse>>> find(@RequestParam(required = false) String email,
                                                              @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                              @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize) {

        ApiResponse<PageableResponse<UserResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(userService.find(email, pageNo, pageSize));

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
