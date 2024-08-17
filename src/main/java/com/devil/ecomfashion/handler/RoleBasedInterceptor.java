package com.devil.ecomfashion.handler;

import com.devil.ecomfashion.constant.URLConstant;
import com.devil.ecomfashion.modules.user.constants.Role;
import com.devil.ecomfashion.modules.user.constants.RolePermissionMapping;
import com.devil.ecomfashion.modules.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleBasedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Get the current authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the request URI
            String requestUri = request.getRequestURI();
            User user = (User) authentication.getPrincipal();
            // Example role-based access control
            if (!HttpMethod.GET.name().equalsIgnoreCase(request.getMethod()) && requestUri.startsWith(URLConstant.CATEGORY_BASE)
                    || requestUri.startsWith(URLConstant.SUBCATEGORY_BASE) || requestUri.startsWith(URLConstant.PRODUCT_BASE)) {

//                if (authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority()role.getAuthority().equals("ROLE_ADMIN"))) {
                if (user.getRole().equals(Role.ADMIN)) {
                    return true; // User has ADMIN role, proceed with request
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: ADMIN role required");
                    return false; // User does not have the required role
                }
//            } else if (requestUri.startsWith("/api/v1/user")) {
//                if (authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_USER") || role.getAuthority().equals("ROLE_ADMIN"))) {
//                    return true; // User has USER or ADMIN role, proceed with request
//                } else {
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: USER role required");
//                    return false; // User does not have the required role
//                }
            }
            // Add more role-based logic as needed
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Please log in");
        return false; // User is not authenticated
    }
}
