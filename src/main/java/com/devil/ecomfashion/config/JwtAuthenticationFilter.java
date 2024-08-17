package com.devil.ecomfashion.config;

import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.constant.URLConstant;
import com.devil.ecomfashion.exception.CustomAuthenticationException;
import com.devil.ecomfashion.modules.token.entity.Token;
import com.devil.ecomfashion.modules.token.respository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains(URLConstant.AUTH_BASE)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(Message.AUTHORIZATION);
        final String jwt;
        final String userEmail;

        if (authHeader == null || ! authHeader.startsWith(Message.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        userEmail = jwtService.extractUserName(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            Optional<Token> token = tokenRepository.findByToken(jwt);

            boolean isTokenValid = false;
            if (token.isPresent()) {
                isTokenValid = (! token.get().isExpired() && ! token.get().isRevoked());

                if (token.get().isRevoked()) {
                    throw  new CustomAuthenticationException(Message.USER_NOT_FOUND);
                }
                request.setAttribute(Message.REQUEST_ATTRIBUTE_USER,token.get().getUser());
            }

            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
