package com.kakao.cafe.adapter.in.presentation.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.application.article.dto.ArticleInfo;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.domain.article.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ArticleInfoController.class)
class ArticleInfoControllerTest {

    @MockBean
    GetArticleInfoUseCase getArticleInfoUseCase;
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("게시글 목록 출력 테스트")
    @Test
    void displayArticleList() throws Exception {
        // given
        String url = "/";
        List<ArticleInfo> givenArticleList = new ArrayList<>();
        givenArticleList.add(new ArticleInfo(5, "kakao", "krew", "2022-01-10 20:00"));
        givenArticleList.add(new ArticleInfo(10, "HaChanho", "champ", "2022-01-11 21:00"));
        given(getArticleInfoUseCase.getListOfAllArticles()).willReturn(ArticleList.from(givenArticleList));

        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.TEXT_HTML))
                                  .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                                  .andReturn();

        List<ArticleInfo> responseUserList = (List<ArticleInfo>) Objects.requireNonNull(result.getModelAndView())
                                                                        .getModelMap().get("articles");

        // then
        for (int i = 0; i < givenArticleList.size(); i++) {
            assertThat(givenArticleList.get(i)).isEqualTo(responseUserList.get(i));
        }
    }

    @DisplayName("게시글 상세보기 테스트")
    @Test
    void displayArticleDetail() throws Exception {
        // given
        int id = 5;
        String writer = "champ";
        String title = "HaChanho";
        String contents = "champ@kakao.com";
        String createdAt = "2022-01-10 15:23";
        Article givenArticle = new Article.Builder().writer(writer)
                                                    .title(title)
                                                    .contents(contents)
                                                    .createdAt(createdAt)
                                                    .build();
        String url = "/articles/" + id;
        given(getArticleInfoUseCase.getArticleDetail(id)).willReturn(givenArticle);

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.TEXT_HTML))
                                  .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                                  .andReturn();

        Article article = (Article) Objects.requireNonNull(result.getModelAndView()).getModelMap().get("article");

        //then
        assertThat(article.getId()).isEqualTo(givenArticle.getId());
        assertThat(article.getWriter()).isEqualTo(givenArticle.getWriter());
        assertThat(article.getTitle()).isEqualTo(givenArticle.getTitle());
        assertThat(article.getContents()).isEqualTo(givenArticle.getContents());
        assertThat(article.getCreatedAt()).isEqualTo(givenArticle.getCreatedAt());
    }
}
