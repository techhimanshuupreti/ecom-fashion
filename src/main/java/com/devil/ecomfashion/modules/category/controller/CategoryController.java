package com.devil.ecomfashion.modules.category.controller;

import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.category.dto.CategoryDTO;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ap1/v1/category")
@CrossOrigin(origins = {"http://localhost:3000/", "*"})
@RequiredArgsConstructor
@Validated
@Tag(name = "Categories", description = "Categories related api")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create(@Valid @RequestBody CategoryDTO categoryDTO) {

        ApiResponse<Category> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(categoryService.create(categoryDTO));

        return apiResponseModel.createResponse();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> find(@RequestParam(required = false) String name) {

        ApiResponse<List<Category>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(categoryService.find(name));

        return apiResponseModel.createResponse();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Category>> findById(@PathVariable long id) {

        ApiResponse<Category> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(categoryService.findById(id));

        return apiResponseModel.createResponse();
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Boolean>> delete(@RequestParam String name) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setResult(categoryService.delete(name));

        return apiResponse.createResponse();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<Category>> update(@PathVariable long id,@Valid @RequestBody CategoryDTO categoryDTO) {
        ApiResponse<Category> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setResult(categoryService.update(id,categoryDTO));

        return apiResponse.createResponse();
    }

}
