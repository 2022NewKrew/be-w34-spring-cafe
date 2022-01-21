package com.kakao.cafe.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import com.kakao.cafe.exception.ErrorCode;
import java.util.UUID;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
class UserControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(sharedHttpSession())
                .build();
    }

    @DisplayName("모든 회원 리스트 조회 default 회원 2명")
    @Test
    void getUserList() throws Exception {
        mvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users/list"))
                .andExpect(model().attribute("userList", IsCollectionWithSize.hasSize(2)))
                .andExpect(content().string(containsString("test")));
    }

    @DisplayName("올바른 조건으로 회원 가입")
    @Test
    void register_CorrectInfo() throws Exception {
        MockHttpServletRequestBuilder request = post("/users")
                .param("userId", "user1")
                .param("password", "password123")
                .param("name", "user1")
                .param("email", "user1@test.com");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @DisplayName("중복 userID로 회원 가입")
    @Test
    void register_DuplicatedUserId() throws Exception {
        MockHttpServletRequestBuilder request = post("/users")
                .param("userId", "test")
                .param("password", "password123")
                .param("name", "user1")
                .param("email", "user1@test.com");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"))
                .andExpect(model().attribute("message", ErrorCode.USERID_DUPLICATION.getMessage()));
    }

    @DisplayName("10자 미만 비밀번호로 회원 가입")
    @Test
    void register_PasswordLessThan10() throws Exception {
        MockHttpServletRequestBuilder request = post("/users")
                .param("userId", "user1")
                .param("password", "123456789")
                .param("name", "user1")
                .param("email", "user1@test.com");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"))
                .andExpect(content().string(containsString("비밀번호는 10-20자 이어야 합니다.")));
    }

    @DisplayName("로그아웃 상태에서 회원 프로필 조회")
    @Test
    void getUserById_Logout() throws Exception {
        UUID id = new UUID(0, 0);

        mvc.perform(get("/users/{id}", id))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }

    @DisplayName("존재하는 id로 회원 프로필 조회")
    @Test
    void getUserById_ExistId() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 0);

        mvc.perform(get("/users/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users/profile"))
                .andExpect(model().attributeExists("user"))
                .andExpect(content().string(containsString("test")));
    }

    @DisplayName("존재하지 않는 id로 회원 프로필 조회")
    @Test
    void getUserById_NotExistId() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 1);

        mvc.perform(get("/users/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/error"))
                .andExpect(model().attribute("message", ErrorCode.USER_NOT_FOUND.getMessage()));
    }

    @DisplayName("올바른 정보로 로그인")
    @Test
    void login_CorrectInfo() throws Exception {
        MockHttpServletRequestBuilder request = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("존재하지 않는 사용자 아이디로 로그인")
    @Test
    void login_UserNotFound() throws Exception {
        MockHttpServletRequestBuilder request = post("/users/login")
                .param("userId", "test123")
                .param("password", "test123456");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"))
                .andExpect(model().attribute("message", ErrorCode.LOGIN_USER_NOT_FOUND.getMessage()));
    }

    @DisplayName("잘못된 비밀번호로 로그인")
    @Test
    void login_WrongPassword() throws Exception {
        MockHttpServletRequestBuilder request = post("/users/login")
                .param("userId", "test")
                .param("password", "wrong-password");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"))
                .andExpect(model().attribute("message", ErrorCode.LOGIN_WRONG_PASSWORD.getMessage()));
    }
}
