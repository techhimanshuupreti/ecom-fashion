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

        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(URLConstant.ENDPOINT_WHITELIST.toArray(new String[0])).permitAll()
                        .requestMatchers(HttpMethod.GET, URLConstant.ALL_ENDPOINT_WHITELIST.toArray(new String[0])).permitAll()
                        .requestMatchers(HttpMethod.POST, URLConstant.ADMIN_ENDPOINTS.toArray(new String[0])).hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH, URLConstant.ADMIN_ENDPOINTS.toArray(new String[0])).hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, URLConstant.ADMIN_ENDPOINTS.toArray(new String[0])).hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, URLConstant.ADMIN_ENDPOINTS.toArray(new String[0])).hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl(URLConstant.AUTH_BASE + URLConstant.USER_LOGOUT)
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(URLConstant.STRIKE);
        config.addAllowedHeader(URLConstant.STRIKE);
        config.addAllowedMethod(URLConstant.STRIKE);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(URLConstant.DOUBLE_STRIKE, config);
        return source;
    }
}