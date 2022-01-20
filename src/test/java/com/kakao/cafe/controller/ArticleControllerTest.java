package com.kakao.cafe.controller;

import com.kakao.cafe.core.SessionConst;
import com.kakao.cafe.domain.user.User;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class ArticleControllerTest {
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
    void 게시글() {
//        mockMvc.p
    }
}
