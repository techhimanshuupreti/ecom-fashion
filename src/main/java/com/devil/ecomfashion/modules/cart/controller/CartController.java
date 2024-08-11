package com.devil.ecomfashion.modules.cart.controller;
;
import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.modules.cart.dto.request.CartRequestDTO;
import com.devil.ecomfashion.modules.cart.entity.Cart;
import com.devil.ecomfashion.modules.cart.service.CartService;
import com.devil.ecomfashion.modules.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@Tag(name = "Carts", description = "Carts related api")
@RestController
@RequestMapping("/api/v1/carts")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponse<Cart>> create(@RequestAttribute User user, RequestBody com.devil.ecomfashion.modules.cart.dto.request.CartRequestDTO cartRequest) {
        ApiResponse<Cart> apiResponseModel = new ApiResponse<>();
        apiResponseModel.setSuccess(true);
        apiResponseModel.setResult(cartService.create(cartRequestDTO, user));

        return apiResponseModel.createResponse();
    }
}