package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.Contents;
import com.kakao.cafe.article.domain.Title;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.ArticleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    private static Article article;
    private static Article articleContainsId;
    @InjectMocks
    private ArticleService articleService;
    @Mock
    private ArticleRepository articleRepository;

    @Test
    @BeforeAll
    static void setUp() {
        article = new Article(new UserId("test123"), new Date(), new Title("123456"), new Contents("테스트용"));
        articleContainsId = new Article(1L, article);
    }

    @Test
    @DisplayName("ArticleId로 Article을 찾을 때, ArticleId가 없으면 에러를 반환한다.")
    void findArticleByNoExistArticleId() {
        when(articleRepository.findByArticleId(article.getArticleId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.findByArticleId(0L))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.ARTICLE_NOT_FOUND.getErrorMessage());
    }

    @Test
    @DisplayName("ArticleId로 Article을 찾을 때, ArticleId가 있으면 Article을 반환한다.")
    void findArticleByExistArticleId() {
        when(articleRepository.findByArticleId(articleContainsId.getArticleId()))
                .thenReturn(Optional.of(articleContainsId));

        assertThat(articleService.findByArticleId(articleContainsId.getArticleId()))
                .isEqualTo(articleContainsId);
    }

    @Test
    @DisplayName("저장된 Article 전체를 가져온다.")
    void findAllArticles() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1L, article));
        articles.add(new Article(2L, article));
        when(articleRepository.findAll()).thenReturn(articles);

        assertThat(articleService.findArticles())
                .isEqualTo(articles);
    }
}
