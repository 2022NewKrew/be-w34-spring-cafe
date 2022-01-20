package com.kakao.cafe.application.article;

import com.kakao.cafe.application.article.validation.ArticleErrorCode;
import com.kakao.cafe.application.article.validation.NonExistsArticleIdException;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.FindArticlePort;
import com.kakao.cafe.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class FindArticleServiceTest {

    @InjectMocks
    FindArticleService findArticleService;

    @Mock
    FindArticlePort findArticlePort;

    private User createStaticUser() {
        return User.builder()
                .userId("483759")
                .name("윤이진")
                .password("password")
                .email("483759@naver.com")
                .build();
    }

    @DisplayName("글 ID로 특정 글을 조회할 수 있다")
    @Test
    void checkFindArticleById() {
        // given
        int articleId = 123;
        Article expectedArticle = new Article(articleId, createStaticUser(), LocalDateTime.of(2022, 1, 12, 17, 0), "Hello", "World");
        given(findArticlePort.findById(articleId))
                .willReturn(Optional.ofNullable(expectedArticle));

        // when
        Article article = findArticleService.findById(articleId);

        //then
        assertThat(article)
                .usingRecursiveComparison()
                .isEqualTo(article);
        verify(findArticlePort).findById(articleId);
    }

    @DisplayName("존재하지 않는 글 ID로 조회를 시도하면 에러가 발생한다")
    @Test
    void checkFindNonExistArticleByIdThrowsException() {
        // given
        int articleIdThatDoesNotExist = 123;
        given(findArticlePort.findById(articleIdThatDoesNotExist))
                .willReturn(Optional.empty());

        // when
        NonExistsArticleIdException exception = assertThrows(NonExistsArticleIdException.class, () -> findArticleService.findById(articleIdThatDoesNotExist));

        //then
        assertThat(exception.getMessage())
                .isEqualTo(ArticleErrorCode.NON_EXISTS_ARTICLE_INDEX.getMessage());
        verify(findArticlePort).findById(articleIdThatDoesNotExist);
    }

    @DisplayName("모든 글 목록을 확인할 수 있다")
    @Test
    void checkFindAllArticleList() {
        // given
        List<Article> expectedArticles = List.of(
                new Article(0, createStaticUser(), LocalDateTime.of(2022, 1, 12, 16, 30), "Hello", "World"),
                new Article(1, createStaticUser(), LocalDateTime.of(2022, 1, 12, 16, 30), "Hello2", "World2")
        );
        given(findArticlePort.findAll())
                .willReturn(expectedArticles);

        // when
        List<Article> articleList = findArticleService.findAll();

        //then
        assertThat(articleList)
                .extracting("id", "writer.name", "title", "contents")
                .containsExactly(
                        tuple(0, "윤이진", "Hello", "World"),
                        tuple(1, "윤이진", "Hello2", "World2")
                );
        verify(findArticlePort).findAll();
    }

}