package com.devil.ecomfashion.modules.cart.controller;

;
import com.devil.ecomfashion.constant.Constants;
import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.constant.URLConstant;
import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.cart.dto.request.CartRequestDTO;
import com.devil.ecomfashion.modules.cart.dto.response.CartResponse;
import com.devil.ecomfashion.modules.cart.service.CartService;
import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.utils.PageableResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@Tag(name = Message.CART_CONTROLLER_TAG, description = Message.CART_DESCRIPTION)
@RestController
@RequestMapping(URLConstant.CART_BASE)
@CrossOrigin(origins = URLConstant.STRIKE)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponse<CartResponse>> create(@RequestAttribute User user, @Valid CartRequestDTO cartRequestDTO) {
        ApiResponse<CartResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(cartService.create(cartRequestDTO, user));

        return apiResponseModel.createResponse();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageableResponse<CartResponse>>> findAll(@RequestAttribute User user,
                                                                 @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int pageNo,
                                                                 @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize) {
        ApiResponse<PageableResponse<CartResponse>> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(cartService.findAll(user, pageNo, pageSize));

        return apiResponseModel.createResponse();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@RequestAttribute User user, @PathVariable Long id) {
        ApiResponse<Boolean> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(cartService.delete(user, id));

        return apiResponseModel.createResponse();
    }

    @PatchMapping("{id}/qty/{qty}")
    public ResponseEntity<ApiResponse<CartResponse>> updateQty(@RequestAttribute User user, @PathVariable Long id, @PathVariable int qty) {
        ApiResponse<CartResponse> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(cartService.updateQty(user, id, qty));

        return apiResponseModel.createResponse();
    }
}