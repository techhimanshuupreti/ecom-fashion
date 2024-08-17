package com.devil.ecomfashion.config;

import com.devil.ecomfashion.handler.RoleBasedInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RoleBasedInterceptor roleBasedInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleBasedInterceptor)
                .addPathPatterns("/api/v1/categories",
                        "/api/v1/sub-categories",
                        "/api/v1/products"); // Apply interceptor to all paths under /api/v1/
    }
}