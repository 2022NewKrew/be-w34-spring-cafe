package com.kakao.cafe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() throws Exception {
        this.mockMvc.perform(get("/users/deleteAll"));
    }

    @Test
    public void user_create_test() throws Exception {
        this.mockMvc.perform(post("/user/create")
                        .param("userId", "yunyul")
                        .param("password", "1q2w3e4r")
                        .param("name", "윤렬")
                        .param("email", "eden.yoon@kakaocorp.com"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void user_dup_error_test() throws Exception {
        this.mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));
        this.mockMvc.perform(post("/user/create")
                        .param("userId", "yunyul")
                        .param("password", "1q2w3e4r")
                        .param("name", "윤렬")
                        .param("email", "eden.yoon@kakaocorp.com"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void user_update_test() throws Exception {
        this.mockMvc.perform(post("/user/create")
                        .param("userId", "yunyul")
                        .param("password", "1q2w3e4r")
                        .param("name", "윤렬")
                        .param("email", "eden.yoon@kakaocorp.com"))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(post("/users/yunyul")
                        .param("userId", "yunyul")
                        .param("oldPassword", "1q2w3e4r")
                        .param("password", "1q2w3e5t")
                        .param("name", "new윤렬")
                        .param("email", "eden.yoon@kakao.com"))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/users/yunyul"))
                .andExpect(model().attribute("user",
                        allOf(
                                hasProperty("password", is("1q2w3e5t")),
                                hasProperty("name", is("new윤렬")),
                                hasProperty("email", is("eden.yoon@kakao.com"))
                        )
                ));
    }
}
