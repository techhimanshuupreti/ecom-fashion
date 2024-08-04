package com.devil.ecomfashion.controller;

import com.devil.ecomfashion.config.SpringSecurityTestConfig;
import com.devil.ecomfashion.modules.user.controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringSecurityTestConfig.class)    //Passing the security config class details.
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private UserController userController;

    @MockBean
    private SecurityContext securityContext;

    @Test
    void contextLoads() {

        Assertions.assertNotNull(userController);
    }

    // Testing if the user with ROLE_ADMIN is able to access the admin api.
    @Test
    @WithUserDetails("testemail@gmail.comm")
    public void adminMessageTestSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
