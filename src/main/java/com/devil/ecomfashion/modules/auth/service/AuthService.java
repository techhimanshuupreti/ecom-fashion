package com.devil.ecomfashion.modules.auth.service;


import com.devil.ecomfashion.config.JwtService;
import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.exception.ExceptionOccur;
import com.devil.ecomfashion.exception.AlreadyExistException;
import com.devil.ecomfashion.modules.auth.dto.AuthDTO;
import com.devil.ecomfashion.modules.auth.model.AuthResponse;
import com.devil.ecomfashion.modules.token.constants.TokenType;
import com.devil.ecomfashion.modules.token.entity.Token;
import com.devil.ecomfashion.modules.token.respository.TokenRepository;
import com.devil.ecomfashion.modules.user.dto.UserDTO;
import com.devil.ecomfashion.modules.user.constants.Role;
import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.respository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenRepository tokenRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public User register(UserDTO userDTO) {
        try{
            List<User> isUserExist = userRepository.findByEmail(userDTO.getEmail());

            if (ObjectUtils.allNotNull(isUserExist) && ! isUserExist.isEmpty()) {
                log.error("user already found for {}", userDTO.getEmail());
                throw new AlreadyExistException(Message.USER_FOUND);
            }

            User user = User.builder()
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(Role.USER)
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            log.info("user creating for {}",userDTO.getEmail());
            return userRepository.save(user);
        }catch (Exception e){
            log.error("exception occur while register for user details : {}",userDTO.getEmail());
            throw new ExceptionOccur("Unable to register for user.");
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        tokenRepository.save(token);
    }

    public AuthResponse authenticate(AuthDTO authDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.getEmail(),
                        authDTO.getPassword()
                )
        );

        User user = userRepository.findUserByEmail(authDTO.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .userId(String.valueOf(user.getId()))
                .role(user.getRole().name())
                .build();
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());

        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
            token.setUpdatedAt(new Date());
        });

        tokenRepository.saveAll(validUserTokens);

    }

    public AuthResponse refreshToken(HttpServletRequest request) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        final String refreshToken;

        final String userEmail;

        if (authHeader == null || ! authHeader.startsWith(Message.BEARER)) {
            return AuthResponse.builder()
                    .build();
        }

        refreshToken = authHeader.substring(7);

        userEmail = jwtService.extractUserName(refreshToken);

        AuthResponse authResponse = null;
        if (userEmail != null) {
            Optional<User> user = this.userRepository.findUserByEmail(userEmail);
            if(user.isEmpty()){
                return AuthResponse.builder()
                        .build();
            }

            if (jwtService.isTokenValid(refreshToken, user.get())) {
                String accessToken = jwtService.generateToken(user.get());
                revokeAllUserTokens(user.get());
                saveUserToken(user.get(), accessToken);
                authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .userId(String.valueOf(user.get().getId()))
                        .role(user.get().getRole().name())
                        .build();
            }
        }
        return authResponse;
    }

}
