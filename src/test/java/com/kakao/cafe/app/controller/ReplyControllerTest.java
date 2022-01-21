package com.kakao.cafe.app.controller;

import com.kakao.cafe.domain.exception.NoSuchObjectException;
import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReplyController.class)
@MockBean(ReplyService.class)
class ReplyControllerTest {

    @Autowired
    private ReplyService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void write() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);
        RequestBuilder request = post("/articles/5678/replies")
                .param("title", "title")
                .param("contents", "content")
                .session(session);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void write_titleTooLong() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);
        RequestBuilder request = post("/articles/5678/replies")
                .param("title".repeat(9999), "title")
                .param("contents", "content")
                .session(session);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void write_unauthenticated() throws Exception {
        RequestBuilder request = post("/articles/5678/replies")
                .param("title", "title")
                .param("contents", "content");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void write_noSuchUser() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);
        RequestBuilder request = post("/articles/5678/replies")
                .param("title", "title")
                .param("contents", "content")
                .session(session);
        when(service.create(anyLong(), anyLong(), any())).thenAnswer(invocation -> {
            throw new NoSuchUserException();
        });

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void write_noSuchArticle() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);
        RequestBuilder request = post("/articles/5678/replies")
                .param("title", "title")
                .param("contents", "content")
                .session(session);
        when(service.create(anyLong(), anyLong(), any())).thenAnswer(invocation -> {
            throw new NoSuchObjectException();
        });

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
