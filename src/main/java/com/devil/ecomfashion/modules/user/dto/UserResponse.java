package com.devil.ecomfashion.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private Date createdAt;

    private Date updatedAt;
}
