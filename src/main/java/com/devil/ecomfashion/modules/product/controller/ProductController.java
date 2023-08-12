package com.devil.ecomfashion.modules.product.controller;

import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.category.dto.CategoryDTO;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.product.dto.ProductDTO;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.service.ProductService;
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
@RequestMapping("/ap1/v1/product")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Product>> create(@Valid @ModelAttribute ProductDTO productDTO) throws IOException {

        ApiResponse<Product> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.create(productDTO));

        return apiResponseModel.createResponse();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> find() {

        ApiResponse<List<Product>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.find());

        return apiResponseModel.createResponse();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Optional<Product>>> findOne(@PathVariable long id) {

        ApiResponse<Optional<Product>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.findOne(id));

        return apiResponseModel.createResponse();
    }
}
