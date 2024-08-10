package com.devil.ecomfashion.modules.subcategory.controller;

import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.subcategory.dto.request.SubCategoryDTO;
import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import com.devil.ecomfashion.modules.subcategory.service.SubCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sub-categories")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
@Tag(name = "Sub-Category", description = "Sub-categories related api")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<SubCategoryResponse>> create(@Validated
                                                           @RequestBody SubCategoryDTO subCategoryDTO) {

        ApiResponse<SubCategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(subCategoryService.create(subCategoryDTO));
        apiResponse.setSuccess(true);

        return apiResponse.createResponse();

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubCategoryResponse>>> find(@RequestParam(required = false) String name) {

        ApiResponse<List<SubCategoryResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(subCategoryService.find(name));

        return apiResponseModel.createResponse();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<SubCategoryResponse>> findById(@PathVariable long id) {

        ApiResponse<SubCategoryResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(subCategoryService.findById(id));

        return apiResponseModel.createResponse();
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Boolean>> delete(@RequestParam String name) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setResult(subCategoryService.delete(name));

        return apiResponse.createResponse();
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<SubCategoryResponse>> update(@PathVariable long id,@Valid @RequestBody SubCategoryDTO subCategoryDTO) {
        ApiResponse<SubCategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setResult(subCategoryService.update(id,subCategoryDTO));

        return apiResponse.createResponse();
    }
}
