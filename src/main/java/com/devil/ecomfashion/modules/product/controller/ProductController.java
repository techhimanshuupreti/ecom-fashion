package com.devil.ecomfashion.modules.product.controller;

import com.devil.ecomfashion.constant.Constants;
import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.constant.URLConstant;
import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.product.dto.request.ProductDTO;
import com.devil.ecomfashion.modules.product.dto.request.UpdateProductDTO;
import com.devil.ecomfashion.modules.product.dto.response.PageableProductResponse;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.service.ProductService;
import com.devil.ecomfashion.utils.PageableResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(URLConstant.PRODUCT_BASE)
@CrossOrigin(origins = URLConstant.STRIKE)
@RequiredArgsConstructor
@Validated
@Tag(name = Message.PRODUCT, description = Message.PRODUCT_DESCRIPTION)
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
    public ResponseEntity<ApiResponse<PageableResponse<ProductResponse>>> find(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                              @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize) {

        ApiResponse<PageableResponse<ProductResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.find(name, pageNo, pageSize));

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

    @PatchMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable long id, @ModelAttribute UpdateProductDTO updateProductDTO) {

        ApiResponse<ProductResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.updateProduct(id, updateProductDTO));

        return apiResponseModel.createResponse();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ApiResponse< PageableResponse<ProductResponse>>> getProductsByCategory(@PathVariable long id,
                                                                                      @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                                                      @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize) {

        ApiResponse< PageableResponse<ProductResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.getProductsByCategory(id, pageNo, pageSize));

        return apiResponseModel.createResponse();
    }

    @GetMapping("/sub-categories/{id}")
    public ResponseEntity<ApiResponse< PageableResponse<ProductResponse>>> getProductsBySubCategory(@PathVariable long id,
                                                                                         @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                                                         @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize) {

        ApiResponse< PageableResponse<ProductResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(productService.getProductsBySubCategory(id, pageNo, pageSize));

        return apiResponseModel.createResponse();
    }

}
