package com.devil.ecomfashion.modules.user.dto;

import com.devil.ecomfashion.modules.user.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;
}
