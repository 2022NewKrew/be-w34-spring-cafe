package com.kakao.cafe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void requestSignup_InvokedWithValidParameters_RedirectsCorrectly() throws Exception {
        mockMvc.perform(post("/users")
                        .param("userName", "creationTest")
                        .param("password", "creationTest")
                        .param("name", "creationTest")
                        .param("email", "creationTest@email.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    void requestSignup_InvokedWithoutSomeParameters_Status400() throws Exception {
        mockMvc.perform(post("/users")
                        .param("password", "invalidParameterTest")
                        .param("name", "invalidParameterTest")
                        .param("email", "invalidParameterTest@email.com"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/invalidinput"));

        mockMvc.perform(post("/users")
                        .param("userName", "invalidParameterTest")
                        .param("name", "invalidParameterTest")
                        .param("email", "invalidParameterTest@email.com"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/invalidinput"));

        mockMvc.perform(post("/users")
                        .param("userName", "invalidParameterTest")
                        .param("password", "invalidParameterTest")
                        .param("email", "invalidParameterTest@email.com"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/invalidinput"));

        mockMvc.perform(post("/users")
                        .param("userName", "invalidParameterTest")
                        .param("password", "invalidParameterTest")
                        .param("name", "invalidParameterTest"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/invalidinput"));
    }

    @Test
    void requestSignup_DuplicatedUserId_Status409() throws Exception {
        mockMvc.perform(post("/users")
                .param("userName", "duplicationTest")
                .param("password", "duplicationTest")
                .param("name", "duplicationTest")
                .param("email", "duplicationTest@email.com"));

        mockMvc.perform(post("/users")
                        .param("userName", "duplicationTest")
                        .param("password", "duplicationTest")
                        .param("name", "duplicationTest")
                        .param("email", "duplicationTest@email.com"))
                .andExpect(status().isConflict())
                .andExpect(view().name("error/userduplicated"));
    }

    @Test
    void requestUserList_Invoked_ReturnsCorrectModelAndView() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("users/list"));
    }

    @Disabled
    @Test
    void requestUserProfile_InvokedWithValidParameter_ReturnsCorrectModelAndView() throws Exception {
        mockMvc.perform(post("/users")
                .param("userName", "profileTest")
                .param("password", "profileTest")
                .param("name", "profileTest")
                .param("email", "profileTest@email.com"));

        mockMvc.perform(get("/users/profileTest"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("users/profile"));
    }

    @Disabled
    @Test
    void requestUserProfile_InvokedWithNotExistingUserId_Status404() throws Exception {
        mockMvc.perform(get("/users/noSuchUserTest"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/nosuchuser"));
    }
}
