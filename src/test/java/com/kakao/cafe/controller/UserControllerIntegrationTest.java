package com.kakao.cafe.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakao.cafe.exception.CustomException;
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
import org.springframework.validation.BindException;

@SpringBootTest
@Transactional
class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("모든 회원 리스트 조회 default 회원 1명")
    @Test
    void getUserList() throws Exception {
        mvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("userList", IsCollectionWithSize.hasSize(1)));

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
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("중복 userID로 회원 가입")
    @Test
    void register_DuplicatedUserId() throws Exception {
        MockHttpServletRequestBuilder request = post("/users")
                .param("userId", "test")
                .param("password", "password123")
                .param("name", "user1")
                .param("email", "user1@test.com");

        assertThatThrownBy(() -> mvc.perform(request))
                .hasCause(new CustomException(ErrorCode.USERID_DUPLICATION));
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
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException));
    }

    @DisplayName("존재하는 id로 회원 프로필 조회")
    @Test
    void getUserById_ExistId() throws Exception {
        UUID id = new UUID(0, 0);

        mvc.perform(get("/users/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @DisplayName("존재하지 않는 id로 회원 프로필 조회")
    @Test
    void getUserById_NotExistId() throws Exception {
        UUID id = new UUID(0, 1);

        assertThatThrownBy(() -> mvc.perform(get("/users/{id}", id)))
                .hasCause(new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
