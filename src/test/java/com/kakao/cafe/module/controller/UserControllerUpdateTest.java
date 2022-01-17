package com.kakao.cafe.module.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.kakao.cafe.module.model.dto.UserDtos.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerUpdateTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    MockHttpSession session;

    @BeforeEach
    public void init() {
        session = new MockHttpSession();
        session.setAttribute("sessionUser", new UserDto(1L, "rain", "레인", "rain@rain.com"));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void clear() {
        session.clearAttributes();
    }

    @Test
    void 사용자_정보_수정폼() throws Exception {
        mockMvc.perform(get("/users/1/form")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", hasProperty("name", is("레인"))))
                .andExpect(view().name("user/updateForm"));
    }

    @Test
    void 자신이_아닌_사용자_정보_수정_시도() throws Exception {
        mockMvc.perform(get("/users/2/form")
                        .session(session))
                .andExpect(status().isForbidden())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 사용자_정보_수정() throws Exception {
        UserUpdateDto input = new UserUpdateDto(1L, "1111", "2222", "이름 바꿈", "change@c.com");

        mockMvc.perform(put("/users/1")
                        .flashAttr("userUpdateDto", input)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    void 사용자_정보_수정시_틀린_비밀번호_입력() throws Exception {
        UserUpdateDto input = new UserUpdateDto(1L, "2222", "2222", "이름 바꿈", "change@c.com");

        mockMvc.perform(put("/users/1")
                        .flashAttr("userUpdateDto", input)
                        .session(session))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 사용자_정보_수정시_중복_이름_입력() throws Exception {
        UserUpdateDto input = new UserUpdateDto(1L, "1111", "2222", "스노우", "change@c.com");

        mockMvc.perform(put("/users/1")
                        .flashAttr("userUpdateDto", input)
                        .session(session))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }
}
