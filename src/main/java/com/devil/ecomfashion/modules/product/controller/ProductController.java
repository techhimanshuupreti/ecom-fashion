package com.devil.ecomfashion.modules.product.controller;

import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.product.dto.request.ProductDTO;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
@Tag(name = "Product", description = "product related api")
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ProductResponse>> create(@Valid @ModelAttribute ProductDTO productDTO) throws IOException {

        ApiResponse<ProductResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.create(productDTO));

        return apiResponseModel.createResponse();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> find(@RequestParam(required = false) String name) {

        ApiResponse<List<ProductResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.find(name));

        return apiResponseModel.createResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> findOne(@PathVariable long id) {

        ApiResponse<ProductResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.findOne(id));

        return apiResponseModel.createResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable long id) {

        ApiResponse<Boolean> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.delete(id));

        return apiResponseModel.createResponse();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable long id, @RequestBody ProductDTO productDTO) {

        ApiResponse<ProductResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.updateProduct(id,productDTO));

        return apiResponseModel.createResponse();
    }

    @GetMapping("{id}/by-categories")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsByCategory(@PathVariable long id) {

        ApiResponse<List<ProductResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.getProductsByCategory(id));

        return apiResponseModel.createResponse();
    }

    @GetMapping("{id}/by-sub-categories")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsBySubCategory(@PathVariable long id) {

        ApiResponse<List<ProductResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.getProductsBySubCategory(id));

        return apiResponseModel.createResponse();
    }

}
