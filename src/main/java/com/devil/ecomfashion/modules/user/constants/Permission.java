package com.devil.ecomfashion.modules.user.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATED("admin:create"),
    ADMIN_DELETE("admin:delete"),

    MANAGER_UPDATE("management:update"),
    MANAGER_DELETE("management:delete"),
    MANAGER_CREATE("management:create"),
    MANAGER_READ("management:read") ;

    @Getter
    private final String permission;
}
