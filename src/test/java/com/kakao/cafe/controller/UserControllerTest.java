package com.kakao.cafe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.kakao.cafe.domain.SessionUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerTest {

    private static final String SESSION = "sessionUser";
    private static final int firstId = 1;
    private static final String userId = "testUserId";
    private static final String duplicateUserId = userId;
    private static final int notExistId = 9999;
    private static final String password = "testPassword";
    private static final String name = "testName";
    private static final String email = "testEmail@kakaocorp.com";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("[성공] 유저 회원가입")
    void signUp() throws Exception {
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @DisplayName("[실패] 유저 회원가입 - Null 입력")
    @ParameterizedTest(name = "{0}, {1}, {2}, {3}")
    @CsvSource(value = {"null, password, name, email@test", "userId, null, name, email@test",
            "userId, password, null, email@test", "userId, password, name, null"}, nullValues = {"null"})
    void signUp_FailedBy_Null(String userId, String password, String name, String email) throws Exception {
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"));
    }

    @DisplayName("[실패] 유저 회원가입 - 빈 문자열")
    @ParameterizedTest(name = "{0}, {1}, {2}, {3}")
    @CsvSource(value = {"'', password, name, email@test", "userId, '', name, email@test",
            "userId, password, '', email@test", "userId, password, name, ''"})
    void signUp_FailedBy_EmptyString(String userId, String password, String name, String email) throws Exception {
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"));
    }

    @DisplayName("[실패] 유저 회원가입 -잘못된 형식의 이메일")
    @ParameterizedTest(name = "email = {0}")
    @ValueSource(strings = {"test", "test@", "test@@"})
    void signUp_FailedBy_InvalidEmail(String invalidEmail) throws Exception {
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", invalidEmail))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"));
    }

    @Test
    @DisplayName("[실패] 유저 회원가입 - 이미 있는 userID인 경우")
    void signUp_FailedBy_DuplicateUserId() throws Exception {
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        mockMvc.perform(post("/users")
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
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    @DisplayName("[성공] 회원 목록 조회 - 기존 회원이 없는 경우")
    void userList_By_EmptyList() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    @DisplayName("[성공] 프로필 조회")
    void userProfile() throws Exception {
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        mockMvc.perform(get("/users/" + firstId))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"));
    }

    @Test
    @DisplayName("[실패] 프로필 조회 - userId 없음")
    void userProfile_FailedBy_NotExistUserId() throws Exception {
        mockMvc.perform(get("/users/" + notExistId))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/error"));
    }

    @Test
    @DisplayName("[성공] 로그인 성공")
    void login() throws Exception {
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        MockHttpSession session = new MockHttpSession();
        SessionUser sessionUser = new SessionUser(firstId, userId);

        mockMvc.perform(post("/users/login")
                        .session(session)
                        .param("userId", userId)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        SessionUser result = (SessionUser) session.getAttribute(SESSION);

        Assertions.assertFalse(session.isInvalid());
        Assertions.assertEquals(sessionUser.getId(), result.getId());
    }

    @Test
    @DisplayName("[성공] 로그아웃 성공")
    void logout() throws Exception {
        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("name", name)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        MockHttpSession session = new MockHttpSession();
        SessionUser sessionUser = new SessionUser(firstId, userId);
        session.setAttribute(SESSION, sessionUser);

        mockMvc.perform(get("/users/logout")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Assertions.assertTrue(session.isInvalid());
    }
}
