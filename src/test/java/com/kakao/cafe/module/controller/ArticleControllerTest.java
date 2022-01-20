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

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        session = new MockHttpSession();
        session.setAttribute("sessionUser", new UserDtos.UserDto(1L, "rain", "레인", "rain@rain.com"));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void clear() {
        session.clearAttributes();
    }

    @Test
    void 게시물_작성() throws Exception {
        ArticlePostDto input = new ArticlePostDto("제목", "내용");

        mockMvc.perform(post("/articles")
                        .flashAttr("articlePostDto", input)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void 로그인_없이_게시물_작성() throws Exception {
        ArticlePostDto input = new ArticlePostDto("제목", "내용");

        mockMvc.perform(post("/articles")
                        .flashAttr("articlePostDto", input))
                .andExpect(status().isUnauthorized())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("user/login"));
    }

    @Test
    void 게시물_열람() throws Exception {
        mockMvc.perform(get("/articles/1")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attribute("article", hasProperty("author", is("레인"))))
                .andExpect(view().name("article/show"));
    }

    @Test
    void 존재하지_않는_게시물_열람() throws Exception {
        mockMvc.perform(get("/articles/100")
                        .session(session))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 로그인_없이_게시물_열람() throws Exception {
        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isUnauthorized())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("user/login"));
    }

    @Test
    void 게시물_정보_수정폼() throws Exception {
        mockMvc.perform(get("/articles/1/form")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attribute("article", hasProperty("authorId", is(1L))))
                .andExpect(view().name("article/updateForm"));
    }

    @Test
    void 자신이_쓰지_않은_게시물_수정() throws Exception {
        mockMvc.perform(get("/articles/2/form")
                        .session(session))
                .andExpect(status().isForbidden())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 5L})
    void 게시물_수정(long idx) throws Exception {
        ArticleUpdateDto input = new ArticleUpdateDto(idx, 1L, "update title", "update contents");

        mockMvc.perform(put("/articles/" + idx)
                        .flashAttr("articleUpdateDto", input)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles/" + idx));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5})
    void 게시물_삭제(int idx) throws Exception {
        mockMvc.perform(delete("/articles/" + idx)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    void 자신이_쓰지_않은_게시물_삭제(int idx) throws Exception {
        mockMvc.perform(delete("/articles/" + idx)
                        .session(session))
                .andExpect(status().isForbidden())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 댓글이_남아있는_글_삭제() throws Exception {
        mockMvc.perform(delete("/articles/1")
                        .session(session))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }
}