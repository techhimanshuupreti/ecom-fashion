package com.devil.ecomfashion.config;

import com.devil.ecomfashion.constant.URLConstant;
import com.devil.ecomfashion.modules.user.constants.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(req -> {
//                    req.requestMatchers(ENDPOINTS_WHITELIST).permitAll();

                    req.requestMatchers(URLConstant.ENDPOINT_WHITELIST).permitAll();


//                    req.requestMatchers(HttpMethod.GET, URLConstant.CATEGORY_BASE,
//                                                        URLConstant.SUBCATEGORY_BASE,
//                                                        URLConstant.PRODUCT_BASE,
//                                                        URLConstant.PRODUCT_BY_CATEGORY,
//                                                        URLConstant.PRODUCT_BY_SUB_CATEGORY )
//                                    .hasAnyRole(Role.ADMIN.name(), Role.USER.name());

                    req.requestMatchers(HttpMethod.POST, URLConstant.CATEGORY_BASE,
                                    URLConstant.SUBCATEGORY_BASE,
                                    URLConstant.PRODUCT_BASE)
                            .hasRole(Role.ADMIN.name());

                    req.requestMatchers(HttpMethod.PATCH, URLConstant.CATEGORY_BASE,
                                    URLConstant.SUBCATEGORY_BASE,
                                    URLConstant.PRODUCT_BASE)
                            .hasRole(Role.ADMIN.name());

                    req.requestMatchers(HttpMethod.DELETE, URLConstant.CATEGORY_BASE,
                                    URLConstant.SUBCATEGORY_BASE,
                                    URLConstant.PRODUCT_BASE)
                            .hasRole(Role.ADMIN.name());

                    req.requestMatchers(HttpMethod.PUT, URLConstant.CATEGORY_BASE,
                                    URLConstant.SUBCATEGORY_BASE,
                                    URLConstant.PRODUCT_BASE)
                            .hasRole(Role.ADMIN.name());

                    req.anyRequest().authenticated();
                })
                .cors().configurationSource(corsConfigurationSource())
                .and().csrf().disable()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl(URLConstant.AUTH_BASE + URLConstant.USER_LOGOUT)
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(URLConstant.STRIKE);
        config.addAllowedHeader(URLConstant.STRIKE);
        config.addAllowedMethod(URLConstant.STRIKE);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
