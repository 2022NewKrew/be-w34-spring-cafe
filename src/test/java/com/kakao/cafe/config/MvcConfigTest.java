package com.kakao.cafe.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class MvcConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void signupUrlReturnsCorrectView() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/form"));
    }

    @Test
    void articleFormUrlReturnsCorrectView() throws Exception {
        mockMvc.perform(get("/article/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("articles/form"));
    }

    @Test
    void indexUrlReturnsCorrectView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/articles"));
    }
}
