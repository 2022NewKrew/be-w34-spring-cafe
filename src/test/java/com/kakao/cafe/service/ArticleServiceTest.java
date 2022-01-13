package com.kakao.cafe.service;

import com.kakao.cafe.domain.*;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.utility.ArticleException;
import com.kakao.cafe.utility.ErrorCode;
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

import static org.assertj.core.api.Assertions.assertThat;
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
        User user = new User(new UserId("test123"), new Password("&test12345"), new Name("테스트"), new Email("test123@test.com"));
        article = new Article(user, new Date(), new Title("123456"), new Contents("테스트용"));
        articleContainsId = new Article(1L, article);
    }

    @Test
    @DisplayName("Article이 null인 경우 게시할 수 없다.")
    void invalidArticleNull() {
        assertThatThrownBy(() -> articleService.postArticle(null))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @Test
    @DisplayName("Article 동일한 ArticleId가 존재하는 경우 추가할 수 없다.")
    void postSameArticleFailed() {
        when(articleRepository.findByArticleId(articleContainsId.getArticleId()))
                .thenReturn(Optional.of(articleContainsId));

        assertThatThrownBy(() -> articleService.postArticle(articleContainsId))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.DUPLICATE_ARTICLE_ID.getErrorMessage());
    }

    @Test
    @DisplayName("Article을 정상적으로 추가한 경우 Article을 반환한다.")
    void postArticleSuccess() {
        when(articleRepository.findByArticleId(article.getArticleId()))
                .thenReturn(Optional.empty());
        when(articleRepository.save(article))
                .thenReturn(articleContainsId);

        assertThat(articleService.postArticle(article))
                .isEqualTo(articleContainsId);
    }

    @Test
    @DisplayName("ArticleId로 Article을 찾을 때, ArticleId가 없으면 에러를 반환한다.")
    void findArticleByNoExistArticleId() {
        when(articleRepository.findByArticleId(article.getArticleId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.findArticleByArticleId(0L))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.ARTICLE_NOT_FOUND.getErrorMessage());
    }

    @Test
    @DisplayName("ArticleId로 Article을 찾을 때, ArticleId가 있으면 Article을 반환한다.")
    void findArticleByExistArticleId() {
        when(articleRepository.findByArticleId(articleContainsId.getArticleId()))
                .thenReturn(Optional.of(articleContainsId));

        assertThat(articleService.findArticleByArticleId(articleContainsId.getArticleId()))
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
