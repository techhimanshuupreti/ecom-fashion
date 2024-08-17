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
            String requestMethod = request.getMethod();
            User user = (User) authentication.getPrincipal();

            // Example role-based access control
            if (requestUri.startsWith(URLConstant.CATEGORY_BASE)
                    || requestUri.startsWith(URLConstant.SUBCATEGORY_BASE) || requestUri.startsWith(URLConstant.PRODUCT_BASE)) {

                if (!HttpMethod.GET.name().equalsIgnoreCase(requestMethod)) {
                    if (user.getRole().equals(Role.ADMIN)) {
                        return true; // User has ADMIN role, proceed with request
                    } else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: ADMIN role required");
                        return false; // User does not have the required role
                    }
                }else if (HttpMethod.GET.name().equalsIgnoreCase(requestMethod)) {
                    return true; // User has ADMIN role, proceed with request
                }
            }
        }


        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Please log in");
        return false; // User is not authenticated
    }
}
