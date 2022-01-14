package com.kakao.cafe.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.account.dto.AccountDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("정상 가입")
    @Order(1)
    void test1() throws Exception {
        // given
        String content = objectMapper.writeValueAsString(AccountDto.builder()
                .userId("id")
                .password("password")
                .name("name")
                .email("email@aaa.com").build());

        // when
        MvcResult result = this.mockMvc.perform(
                        post("/user")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        // then
        assertThat(result.getResponse().getHeader("Location")).isEqualTo("user/list");
    }

    @Test
    @DisplayName("유저 아이디 검색")
    @Order(2)
    void test2() throws Exception {
        // given
        String userId = "id";
        String name = "name";
        String password = "password";
        String email = "email@aaa.com";

        // when
        MvcResult result = this.mockMvc.perform(
                        get("/user/{userId}", userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        AccountDto user_profile = (AccountDto) result.getModelAndView().getModelMap().get("user_profile");

        // then
        assertThat(user_profile.getUserId()).isEqualTo(userId);
        assertThat(user_profile.getEmail()).isEqualTo(email);
        assertThat(user_profile.getPassword()).isEqualTo(password);
        assertThat(user_profile.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("유저 목록 검색")
    @Order(2)
    void test3() throws Exception {
        MvcResult result = this.mockMvc.perform(
                        get("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        List<AccountDto> users = (List<AccountDto>) result.getModelAndView().getModelMap().get("users");
        assertThat(users.size()).isEqualTo(1);
    }

}
