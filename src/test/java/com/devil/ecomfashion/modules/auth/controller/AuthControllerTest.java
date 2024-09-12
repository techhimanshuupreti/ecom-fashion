package com.devil.ecomfashion.modules.auth.controller;

import com.devil.ecomfashion.model.ApiResponse;
import com.devil.ecomfashion.model.ApiResponseError;
import com.devil.ecomfashion.modules.auth.dto.AuthDTO;
import com.devil.ecomfashion.modules.auth.model.AuthResponse;
import com.devil.ecomfashion.modules.auth.service.AuthService;
import com.devil.ecomfashion.modules.user.constants.Role;
import com.devil.ecomfashion.modules.user.dto.UserDTO;
import com.devil.ecomfashion.modules.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

public class AuthControllerTest {
    @Mock
    AuthService authService;
    @InjectMocks
    AuthController authController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() throws Exception {
        when(authService.register(any(UserDTO.class))).thenReturn(new User(0L, "firstName", "lastName", "email", "password", Role.USER, new GregorianCalendar(2024, Calendar.SEPTEMBER, 12, 22, 7).getTime(), new GregorianCalendar(2024, Calendar.SEPTEMBER, 12, 22, 7).getTime()));

        ResponseEntity<ApiResponse<User>> result = authController.register(new UserDTO("firstName", "lastName", "email", "password", "role"));
        Assert.assertEquals(new ResponseEntity<ApiResponse<User>>(new ApiResponse<User>(new User(0L, "firstName", "lastName", "email", "password", Role.USER, new GregorianCalendar(2024, Calendar.SEPTEMBER, 12, 22, 7).getTime(), new GregorianCalendar(2024, Calendar.SEPTEMBER, 12, 22, 7).getTime()), "message", true, new ApiResponseError()), null, null), result);
    }

    @Test
    public void testAuthenticate() throws Exception {
        when(authService.authenticate(any(AuthDTO.class))).thenReturn(new AuthResponse("accessToken", "refreshToken", "userId", "role"));

        ResponseEntity<ApiResponse<AuthResponse>> result = authController.authenticate(new AuthDTO("email", "password"));
        Assert.assertEquals(new ResponseEntity<ApiResponse<AuthResponse>>(new ApiResponse<AuthResponse>(new AuthResponse("accessToken", "refreshToken", "userId", "role"), "message", true, new ApiResponseError()), null, null), result);
    }

    @Test
    public void testRefreshToken() throws Exception {
        when(authService.refreshToken(any(HttpServletRequest.class))).thenReturn(new AuthResponse("accessToken", "refreshToken", "userId", "role"));

        ResponseEntity<ApiResponse<AuthResponse>> result = authController.refreshToken(null);
        Assert.assertEquals(new ResponseEntity<ApiResponse<AuthResponse>>(new ApiResponse<AuthResponse>(new AuthResponse("accessToken", "refreshToken", "userId", "role"), "message", true, new ApiResponseError()), null, null), result);
    }

    @Test
    public void testLogout() throws Exception {
        authController.logout();
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme