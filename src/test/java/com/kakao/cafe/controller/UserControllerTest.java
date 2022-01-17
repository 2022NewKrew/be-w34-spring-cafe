package com.kakao.cafe.controller;

import com.kakao.cafe.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    private List<User> users;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        this.users = new ArrayList<>();
        this.users.add(new User("abc", "1234", "ab@naver.com"));
        this.users.add(new User("abc2", "12342", "ab2@naver.com"));
    }

    @Test
    @DisplayName("유저 리스트 반환 -> 정상")
    void userList() throws Exception {
//        given(userController.userList()).willReturn(users);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/users")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createUser() throws Exception{
    }

    @Test
    void user() {
    }
}