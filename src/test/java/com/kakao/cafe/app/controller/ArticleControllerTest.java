package com.kakao.cafe.app.controller;

import com.kakao.cafe.domain.exception.NoSuchUserException;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.dto.ArticleDto;
import com.kakao.cafe.service.dto.ReplyDto;
import com.kakao.cafe.service.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticleController.class)
@MockBean(ArticleService.class)
class ArticleControllerTest {

    @Autowired
    private ArticleService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void write() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);
        RequestBuilder request = post("/articles")
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
        RequestBuilder request = post("/articles")
                .param("title".repeat(9999), "title")
                .param("contents", "content")
                .session(session);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void write_unauthenticated() throws Exception {
        RequestBuilder request = post("/articles")
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
        RequestBuilder request = post("/articles")
                .param("title", "title")
                .param("contents", "content")
                .session(session);
        when(service.create(anyLong(), any())).thenAnswer(invocation -> {
            throw new NoSuchUserException();
        });

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void list() throws Exception {
        UserDto author = new UserDto.Builder()
                .name("name")
                .email("email")
                .userId("userId")
                .build();
        List<ArticleDto> articles = List.of(
                new ArticleDto.Builder()
                        .id(1L)
                        .author(author)
                        .title("title1")
                        .content("content1")
                        .createdAt(new Date())
                        .build(),
                new ArticleDto.Builder()
                        .id(2L)
                        .author(author)
                        .title("title2")
                        .content("content2")
                        .createdAt(new Date())
                        .build(),
                new ArticleDto.Builder()
                        .id(3L)
                        .author(author)
                        .title("title3")
                        .content("content3")
                        .createdAt(new Date())
                        .build()
        );
        when(service.list()).thenReturn(articles);

        mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title1")))
                .andExpect(content().string(containsString("title2")))
                .andExpect(content().string(containsString("title3")));
    }

    @Test
    void read() throws Exception {
        UserDto author = new UserDto.Builder()
                .name("author1")
                .email("email")
                .userId("userId")
                .build();
        long id = 1234L;
        ArticleDto article = new ArticleDto.Builder()
                .id(id)
                .author(author)
                .title("title1")
                .content("content1")
                .createdAt(new Date())
                .replies(
                        List.of(
                                new ReplyDto.Builder()
                                        .id(1L)
                                        .author(author)
                                        .content("reply1")
                                        .createdAt(new Date())
                                        .build(),
                                new ReplyDto.Builder()
                                        .id(2L)
                                        .author(author)
                                        .content("reply2")
                                        .createdAt(new Date())
                                        .build()
                        )
                )
                .build();
        when(service.getById(id)).thenReturn(Optional.of(article));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);

        mvc.perform(get("/articles/" + id).session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("author1")))
                .andExpect(content().string(containsString("title1")))
                .andExpect(content().string(containsString("content1")))
                .andExpect(content().string(containsString("reply1")))
                .andExpect(content().string(containsString("reply2")));
    }

    @Test
    void read_unauthenticated() throws Exception {
        mvc.perform(get("/articles/1234"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void read_notFound() throws Exception {
        long id = 1234L;
        when(service.getById(id)).thenReturn(Optional.empty());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);

        mvc.perform(get("/articles/" + id).session(session))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateForm() throws Exception {
        long id = 1234L;
        ArticleDto article = new ArticleDto.Builder()
                .id(id)
                .author(
                        new UserDto.Builder()
                                .name("author1")
                                .email("email")
                                .userId("userId")
                                .build()
                )
                .title("title1")
                .content("content1")
                .build();
        when(service.getById(id)).thenReturn(Optional.of(article));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);

        mvc.perform(get("/articles/" + id + "/form").session(session))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateForm_unauthenticated() throws Exception {
        mvc.perform(get("/articles/1234/form"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void update() throws Exception {
        long id = 1234L;
        ArticleDto article = new ArticleDto.Builder()
                .id(id)
                .author(
                        new UserDto.Builder()
                                .name("author1")
                                .email("email")
                                .userId("userId")
                                .build()
                )
                .title("title1")
                .content("content1")
                .build();
        when(service.getById(id)).thenReturn(Optional.of(article));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);

        MockHttpServletRequestBuilder request = put("/articles/" + id)
                .param("title", "title2")
                .param("content", "content2")
                .session(session);
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles/" + id));
    }

    @Test
    void update_unauthenticated() throws Exception {
        long id = 1234L;
        MockHttpServletRequestBuilder request = put("/articles/" + id);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete_() throws Exception {
        long id = 1234L;
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1234L);

        mvc.perform(delete("/articles/" + id).session(session))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void delete_unauthenticated() throws Exception {
        long id = 1234L;

        mvc.perform(delete("/articles/" + id))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }
}
