package com.devil.ecomfashion.modules.user.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.devil.ecomfashion.modules.user.constants.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

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

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        //        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }

}
