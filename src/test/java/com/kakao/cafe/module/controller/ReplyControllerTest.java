package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.model.dto.UserDtos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.kakao.cafe.module.model.dto.ReplyDtos.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    MockHttpSession session;

    @BeforeEach
    public void init() {
        session = new MockHttpSession();
        session.setAttribute("sessionUser", new UserDtos.UserDto(1L, "rain", "레인", "rain@rain.com"));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void clear() {
        session.clearAttributes();
    }

    @Test
    void 댓글_작성() throws Exception {
        ReplyPostDto input = new ReplyPostDto("테스트 댓글");

        mockMvc.perform(post("/articles/1/reply")
                        .flashAttr("replyPostDto", input)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles/1"));
    }

    @Test
    void 존재하지_않는_글에_댓글_작성() throws Exception {
        ReplyPostDto input = new ReplyPostDto("테스트 댓글");

        mockMvc.perform(post("/articles/100/reply")
                        .flashAttr("replyPostDto", input)
                        .session(session))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4})
    void 댓글_삭제(int idx) throws Exception {
        mockMvc.perform(delete("/articles/1/reply/" + idx)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles/1"));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3})
    void 자신이_쓰지_않은_댓글_삭제(int idx) throws Exception {
        mockMvc.perform(delete("/articles/1/reply/" + idx)
                        .session(session))
                .andExpect(status().isForbidden())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }
}