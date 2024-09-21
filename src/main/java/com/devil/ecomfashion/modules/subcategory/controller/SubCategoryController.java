package com.devil.ecomfashion.modules.subcategory.controller;

import com.devil.ecomfashion.constant.Constants;
import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.constant.URLConstant;
import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.subcategory.dto.request.SubCategoryDTO;
import com.devil.ecomfashion.modules.subcategory.dto.response.PageableSubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import com.devil.ecomfashion.modules.subcategory.service.SubCategoryService;
import com.devil.ecomfashion.utils.PageableResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URLConstant.SUBCATEGORY_BASE)
@CrossOrigin(origins = URLConstant.STRIKE)
@RequiredArgsConstructor
@Validated
@Tag(name = Message.SUBCATEGORY, description = Message.SUBCATEGORY_DESCRIPTION)
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
    public ResponseEntity<ApiResponse<PageableResponse<SubCategoryResponse>>> find(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                              @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize) {

        ApiResponse<PageableResponse<SubCategoryResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(subCategoryService.find(name, pageNo, pageSize));

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
