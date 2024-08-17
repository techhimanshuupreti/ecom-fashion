package com.devil.ecomfashion.modules.auth.dto;

import com.devil.ecomfashion.constant.Message;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {

    @NotBlank(message = Message.EMAIL_REQUIRED)
    private String email;

    @NotBlank(message = Message.PASSWORD_REQUIRED)
    private String password;
}
