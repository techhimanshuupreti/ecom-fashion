package com.devil.ecomfashion.modules.category.controller;

import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.category.dto.request.CategoryDTO;
import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.service.CategoryService;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
@Tag(name = "Categories", description = "Categories related api")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryDTO categoryDTO) {

        ApiResponse<CategoryResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(categoryService.create(categoryDTO));

        return apiResponseModel.createResponse();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> find(@RequestParam(required = false) String name) {

        ApiResponse<List<CategoryResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(categoryService.find(name));

        return apiResponseModel.createResponse();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> findById(@PathVariable long id) {

        ApiResponse<CategoryResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(categoryService.findById(id));

        return apiResponseModel.createResponse();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable long id) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setResult(categoryService.delete(id));

        return apiResponse.createResponse();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@PathVariable long id,@Valid @RequestBody CategoryDTO categoryDTO) {
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setResult(categoryService.update(id,categoryDTO));

        return apiResponse.createResponse();
    }

    @GetMapping("{id}/products")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> findAllProductsBySubCategoryId(@PathVariable long id) {

        ApiResponse<List<ProductResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(categoryService.findAllProductsByCategoryId(id));

        return apiResponseModel.createResponse();
    }

}
