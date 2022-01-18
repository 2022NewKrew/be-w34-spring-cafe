package com.kakao.cafe.module.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Test
    void 사용자_목록() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("userList"))
                .andExpect(view().name("user/list"));
    }

    @Test
    void 사용자_프로필() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", hasProperty("name", is("레인"))))
                .andExpect(view().name("user/profile"));
    }

    @Test
    void 존재하지_않는_사용자() throws Exception {
        mockMvc.perform(get("/users/10"))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }
}