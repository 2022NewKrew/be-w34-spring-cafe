package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.Contents;
import com.kakao.cafe.article.domain.Title;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.user.service.UserService;
import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.ArticleException;
import com.kakao.cafe.util.exception.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticlePostServiceTest {

    private static Article article;
    private static Article articleContainsId;
    @InjectMocks
    private ArticlePostService articlePostService;
    @Mock
    private UserService userService;
    @Mock
    private ArticleRepository articleRepository;

    @Test
    @BeforeAll
    static void setUp() {
        article = new Article(new UserId("test123"), new Date(), new Title("123456"), new Contents("테스트용"));
        articleContainsId = new Article(1L, article);
    }

    @Test
    @DisplayName("Article이 null인 경우 게시할 수 없다.")
    void invalidArticleNull() {
        assertThatThrownBy(() -> articlePostService.postArticle(null))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @Test
    @DisplayName("동일한 ArticleId가 존재하는 경우 Article을 게시할 수 없다.")
    void postSameArticleFailed() {
        when(articleRepository.findByArticleId(articleContainsId.getArticleId()))
                .thenReturn(Optional.of(articleContainsId));

        assertThatThrownBy(() -> articlePostService.postArticle(articleContainsId))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.DUPLICATE_ARTICLE_ID.getErrorMessage());
    }

    @Test
    @DisplayName("WriterId가 존재하지 않는 UserId인 경우 Article을 게시할 수 없다.")
    void postFailedNoExistUserId() {
        when(userService.findByUserId(article.getWriterId()))
                .thenThrow(new UserException(ErrorCode.USER_NOT_FOUND));

        assertThatThrownBy(() -> articlePostService.postArticle(articleContainsId))
                .isInstanceOf(UserException.class)
                .hasMessageMatching(ErrorCode.USER_NOT_FOUND.getErrorMessage());
    }

    @Test
    @DisplayName("Article을 정상적으로 추가한 경우 Article을 반환한다.")
    void postArticleSuccess() {
        when(articleRepository.findByArticleId(article.getArticleId()))
                .thenReturn(Optional.empty());
        when(articleRepository.save(article))
                .thenReturn(articleContainsId);

        assertThat(articlePostService.postArticle(article))
                .isEqualTo(articleContainsId);
    }
}
