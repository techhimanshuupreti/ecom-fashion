package com.devil.ecomfashion.modules.user.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.devil.ecomfashion.modules.user.constants.Permission.*;
import static com.devil.ecomfashion.modules.user.constants.Permission.USER_READ;

@Getter
@RequiredArgsConstructor
public enum RolePermissionMapping {
    USER(Set.of(
            USER_CREATE,
            USER_UPDATE,
            USER_DELETE,
            USER_READ
    )),

    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE,
            USER_CREATE,
            USER_UPDATE,
            USER_DELETE,
            USER_READ
    )

    );

    private final Set<Permission> value;

    public static Set<Permission> getPermissions(Role role) {
        Set<Permission> permissions = new HashSet<>();
        if (ADMIN.name().equals(role.name())) {
            permissions = ADMIN.value;
        } else if (USER.name().equals(role.name())) {
            permissions = USER.value;
        }
        return permissions;
    }

}
