package com.devil.ecomfashion.modules.user.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER,ADMIN;

    public List<SimpleGrantedAuthority> getAuthorities(Role role) {
        //        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return RolePermissionMapping.getPermissions(role)
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }
}
