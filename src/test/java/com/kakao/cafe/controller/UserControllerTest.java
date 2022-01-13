package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private static User user;
    @Autowired
    private UserRepository userH2Repository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        userH2Repository.deleteById("test");
        user = new User();
        user.setUserId("test");
        user.setPassword("123");
        user.setName("김근욱");
        user.setEmail("test@test.com");
        userH2Repository.save(user);
    }

    @Test
    @DisplayName("전체 회원 조회 테스트")
    void userList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("회원 생성 후 redirection 테스트")
    void signUp() throws Exception {
        userH2Repository.deleteById("test");
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .param("userId", user.getUserId())
                        .param("password", "123")
                        .param("name", "김근욱")
                        .param("email", "test@test.com"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @DisplayName("특정 회원 조회 테스트")
    void userInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("name", user.getName()))
                .andExpect(MockMvcResultMatchers.model().attribute("email", user.getEmail()));
    }

    @Test
    @DisplayName("회원가입 페이지 조회 테스트")
    void singUpPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/signup"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("로그인 페이지 조회 테스트")
    void loginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void login() throws Exception {

    }

    @AfterEach
    void init() {
        userH2Repository.deleteById("test");
    }
}