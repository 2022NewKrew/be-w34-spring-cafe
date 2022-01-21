package com.kakao.cafe.controller;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class,
        properties = "spring.cloud.vault.fail-fast=false")
class UserControllerTest {

    private static final long FIRST_USER_ID = 1L;
    private static final long SECOND_USER_ID = 2L;
    private static final String USERNAME = "username";
    private static final String NICKNAME = "nickname";
    private static final String EMAIL = "user@mail.com";
    private static final String PASSWORD = "pwd";
    private static final User mockUser = new User(FIRST_USER_ID, USERNAME, NICKNAME, EMAIL, PASSWORD);

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("[GET] /users - 전체 유저의 목록을 조회할 수 있다")
    void users() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    @DisplayName("[POST] /users - 신규 유저 회원가입을 할 수 있다")
    void signUp() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", USERNAME)
                        .param("nickname", NICKNAME)
                        .param("email", EMAIL)
                        .param("password", PASSWORD)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    @DisplayName("[GET] /users/{userId} - 존재하는 유저의 상세정보를 조회할 수 있다")
    void profile() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));

        Mockito.when(userService.findById(FIRST_USER_ID))
                .thenReturn(new UserDto(mockUser));

        mockMvc.perform(get("/users/" + FIRST_USER_ID)
                        .session(mockSession)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"))
                .andExpect(model().attributeExists("user"));
    }


    @Test
    @DisplayName("[GET] /users/{userId} - 존재하지 않는 유저의 상세정보를 조회할 수 없다")
    void failToGetUserProfile() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));

        Mockito.when(userService.findById(FIRST_USER_ID))
                        .thenThrow(new UserNotFoundException("error test"));

        mockMvc.perform(get("/users/" + FIRST_USER_ID)
                        .session(mockSession)
                )
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"))
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    @DisplayName("[PUT] /users/{userId} - 자신의 상세정보를 변경할 수 있다")
    void update() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));

        mockMvc.perform(put("/users/" + FIRST_USER_ID)
                        .session(mockSession)
                )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("[PUT] /users/{userId} - 다른사람의 상세정보를 변경할 수 없다")
    void failToUpdate() throws Exception {
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("auth", new Auth(FIRST_USER_ID));

        mockMvc.perform(put("/users/" + SECOND_USER_ID)
                        .session(mockSession)
                )
                .andExpect(status().isUnauthorized())
                .andExpect(view().name("error"));
    }
}
