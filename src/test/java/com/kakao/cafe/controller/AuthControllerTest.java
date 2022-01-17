package com.kakao.cafe.controller;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.auth.AuthRequest;
import com.kakao.cafe.exception.InvalidPasswordException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    private static final long FIRST_SESSION_ID = 1L;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "pwd";

    @Autowired
    MockMvc mockMvc;
    @MockBean
    AuthService authService;

    // TODO - MockMvcTest 에서 세션 값을 검증하는 방법 찾고 적용해보기

    @Test
    void login() throws Exception {
        Mockito.when(authService.login(new AuthRequest(USERNAME, PASSWORD)))
                .thenReturn(new Auth(FIRST_SESSION_ID));

        mockMvc.perform(post("/login")
                        .param("username", USERNAME)
                        .param("password", PASSWORD)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void failToLoginWithInvalidPassword() throws Exception {
        Mockito.when(authService.login(new AuthRequest(USERNAME, "invalidPwd")))
                .thenThrow(new InvalidPasswordException("error test"));

        mockMvc.perform(post("/login")
                        .param("username", USERNAME)
                        .param("password", "invalidPwd")
                )
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    void failToLoginWithNotExistsUser() throws Exception {
        Mockito.when(authService.login(new AuthRequest("notExistsUsername", PASSWORD)))
                .thenThrow(new UserNotFoundException("error test"));

        mockMvc.perform(post("/login")
                        .param("username", "notExistsUsername")
                        .param("password", PASSWORD)
                )
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    void logout() throws Exception {
        mockMvc.perform(delete("/logout"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}
