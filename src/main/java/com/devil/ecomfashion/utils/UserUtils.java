package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.user.dto.UserResponse;
import com.devil.ecomfashion.modules.user.entity.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {

    public static UserResponse convert(User user) {

        if (ObjectUtils.isEmpty(user))
            return new UserResponse();

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static List<UserResponse> convert(List<User> users) {

        if(ObjectUtils.isEmpty(users)){
            return new ArrayList<>();
        }
        return users.stream().map(UserUtils::convert)
                .collect(Collectors.toList());
    }

    public static PageableResponse<UserResponse> convert(Page<User> userPage) {

        PageableResponse<UserResponse> userResponsePageableResponse = new PageableResponse<>();
        if (ObjectUtils.isEmpty(userPage)) {
            userResponsePageableResponse.setData(new ArrayList<>());
        }else {
            userResponsePageableResponse.setCurrentPage(userPage.getTotalPages() == 0 ? 0 : userPage.getNumber() + 1);
            userResponsePageableResponse.setTotalPages(userPage.getTotalPages());
            userResponsePageableResponse.setTotalElements(userPage.getNumberOfElements());
            userResponsePageableResponse.setData(convert(userPage.getContent()));
        }

        return userResponsePageableResponse;
    }
}
