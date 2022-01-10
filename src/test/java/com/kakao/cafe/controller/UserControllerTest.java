package com.kakao.cafe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void user_create_test() throws Exception {
        this.mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"))
                .andExpect(status().is3xxRedirection());
    }
}
