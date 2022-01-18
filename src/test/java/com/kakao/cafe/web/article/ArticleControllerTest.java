package com.kakao.cafe.web.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.service.article.ArticleCreateService;
import com.kakao.cafe.service.article.ArticleFindService;
import com.kakao.cafe.web.IndexController;
import com.kakao.cafe.web.article.dto.ArticleCreateRequest;
import com.kakao.cafe.web.article.dto.ArticleListResponse;
import com.kakao.cafe.web.article.dto.ArticleResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ArticleController.class, IndexController.class})
class ArticleControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArticleFindService articleFindService;

    @MockBean
    private ArticleCreateService articleCreateService;

    @DisplayName("게시글 목록 출력 테스트")
    @MethodSource("provideArticles")
    @ParameterizedTest
    public void showArticles(List<Article> articles) throws Exception {
        //given
        String url = "/";
        given(articleFindService.findByAll()).willReturn(articles);
        ArticleListResponse provideArticles = new ArticleListResponse(
                articles.stream()
                        .map(ArticleResponse::new)
                        .collect(Collectors.toList())
        );

        //when
        MvcResult result = mvc.perform(get(url))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        ArticleListResponse responseArticles = ((ArticleListResponse) result.getModelAndView().getModelMap().get("articles"));

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(responseArticles.equals(provideArticles)).isTrue();
    }

    private static Stream<List<Article>> provideArticles() {
        ArticleCreateRequest dto1 = new ArticleCreateRequest(new Title("title1"), new Content("content1"));
        ArticleCreateRequest dto2 = new ArticleCreateRequest(new Title("title2"), new Content("content2"));
        LocalDateTime createdAt = LocalDateTime.now();
        Article article1 = dto1.toEntity();
        Article article2 = dto2.toEntity();

        article1.setCreatedAt(createdAt);
        article2.setCreatedAt(createdAt);

        return Stream.of(
                List.of(article1, article2)
        );
    }
}