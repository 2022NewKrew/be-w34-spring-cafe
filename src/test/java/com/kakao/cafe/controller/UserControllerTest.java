package com.kakao.cafe.controller;

import com.kakao.cafe.core.SessionConst;
import com.kakao.cafe.core.exception.NoSuchUser;
import com.kakao.cafe.domain.login.dto.UserLogin;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.dto.UserJoinForm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = true)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    MockHttpSession session;

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUserId("test");
        user.setEmail("p@d");
        user.setPassword("test");
        user.setName("테스터");
        user.setId(31L);

        session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_COOKIE, user);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void clear() {
        session.clearAttributes();
    }
    @Test
    void 사용자_리스트() throws Exception{

        mockMvc.perform(get("/users/list").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void 사용자_조회() throws Exception {
        mockMvc.perform(get("/users/11"))
                .andExpect(status().isOk());
    }

    @Test
    void 없는_사용자() throws Exception {
        mockMvc.perform(get("/users/2323"))
                .andExpect(status().isOk())
                .andExpect(view().name("/error"));
    }

    @Test
    void 회원가입중복() throws Exception {
        UserJoinForm userDto = new UserJoinForm();
        userDto.setUserId("test");
        userDto.setEmail("test");
        userDto.setName("lsh");
        userDto.setPassword("1234");
        mockMvc.perform(post("/users").flashAttr("userDto", userDto))
                .andExpect(status().isOk())
                .equals(content().toString().contains("사용자 id 중복"));
    }
}
