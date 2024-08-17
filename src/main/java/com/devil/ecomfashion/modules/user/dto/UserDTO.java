package com.devil.ecomfashion.modules.user.dto;

import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.modules.user.constants.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = Message.FIRST_NAME_REQUIRED)
    private String firstName;

    @NotBlank(message = Message.LAST_NAME_REQUIRED)
    private String lastName;

    @NotBlank(message = Message.EMAIL_REQUIRED)
    private String email;

    @NotBlank(message = Message.PASSWORD_REQUIRED)
    private String password;

    private String role;
}
