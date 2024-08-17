package com.devil.ecomfashion.config;

import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.modules.token.respository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader(Message.AUTHORIZATION);
        final String jwt;

        if (authHeader == null || ! authHeader.startsWith(Message.BEARER)) {
            return;
        }

        jwt = authHeader.substring(7);

        var storedToken = tokenRepository.findByToken(jwt).orElse(null);

        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
            response.setStatus(HttpStatus.OK.value());
        }
    }
}
