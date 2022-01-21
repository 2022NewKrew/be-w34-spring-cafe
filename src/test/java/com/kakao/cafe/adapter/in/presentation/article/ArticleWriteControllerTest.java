package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ArticleWriteController.class)
class ArticleWriteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WriteArticleUseCase writeArticleUseCase;

    @DisplayName("게시글 작성 테스트")
    @Test
    void writeArticleTest() throws Exception {
        String writer = "champ";
        String title = "kakao";
        String contents = "kakao krew";
        String url = "/articles";

        // then
        mockMvc.perform(
                   MockMvcRequestBuilders.post(url)
                                         .param("writer", writer)
                                         .param("title", title)
                                         .param("contents", contents)
                                         .accept(MediaType.TEXT_HTML)
               )
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andDo(MockMvcResultHandlers.print());
    }
}
