package com.kakao.cafe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    private static final String userId = "testUserId";
    private static final String duplicateUserId = userId;
    private static final String notExistUserId = "notExistUserId";
    private static final String password = "testPassword";
    private static final String name = "testName";
    private static final String email = "testEmail@kakaocorp.com";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[성공] 유저 회원가입")
    void signUp() throws Exception {
        mockMvc.perform(post("/users/create")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @DisplayName("[실패] 유저 회원가입 - 필수 인자 없음")
    @ParameterizedTest(name = "{0}, {1}, {2}, {3}")
    @CsvSource(value = {"null, password, name, email", "userId, null, name, email", "userId, password, null, email",
            "userId, password, name, null"}, nullValues = {"null"})
    void signUp_FailedBy_EmptyParam(String userId, String password, String name, String email) throws Exception {
        mockMvc.perform(post("/users/create")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"));
    }

    @Test
    @DisplayName("[실패] 유저 회원가입 - 이미 있는 userID인 경우")
    void signUp_FailedBy_DuplicateUserId() throws Exception {
        mockMvc.perform(post("/users/create")
                .param("userId", userId)
                .param("password", password)
                .param("name", name)
                .param("email", email));

        mockMvc.perform(post("/users/create")
                        .param("userId", duplicateUserId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().isConflict())
                .andExpect(view().name("errors/error"));
    }

    @Test
    @DisplayName("[성공] 회원 목록 조회")
    void userList() throws Exception {
        mockMvc.perform(post("/users/create")
                .param("userId", userId)
                .param("password", password)
                .param("name", name)
                .param("email", email));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    @DisplayName("[성공] 회원 목록 조회 - 회원이 없는 경우")
    void userList_By_EmptyList() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    @DisplayName("[성공] 프로필 조회")
    void userProfile() throws Exception {
        mockMvc.perform(post("/users/create")
                .param("userId", userId)
                .param("password", password)
                .param("name", name)
                .param("email", email));

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"));
    }

    @Test
    @DisplayName("[실패] 프로필 조회 - userId 없음")
    void userProfile_FailedBy_NotExistUserId() throws Exception {
        mockMvc.perform(get("/users/" + notExistUserId))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/error"));
    }
}
