package com.kakao.cafe.application;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticlePort;
import com.kakao.cafe.domain.article.ArticleVo;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    ArticleService articleService;

    @Mock
    ArticlePort articlePort;

    @DisplayName("회원은 글 작성을 할 수 있다")
    @Test
    void checkWriteArticleSuccessfully() {
        // given
        ArticleVo articleVo = new ArticleVo("윤이진", "Hello", "World");

        // when
        articleService.write(articleVo);

        //then
        verify(articlePort).save(any(ArticleVo.class));
    }

    @DisplayName("글 ID로 특정 글을 조회할 수 있다")
    @Test
    void checkFindArticleById() {
        // given
        int articleId = 123;
        Article expectedArticle = new Article(articleId, "윤이진", LocalDateTime.of(2022, 1, 12, 17, 0), "Hello", "World");
        given(articlePort.findById(articleId))
                .willReturn(Optional.ofNullable(expectedArticle));

        // when
        Article article = articleService.findById(articleId);

        //then
        assertThat(article)
                .usingRecursiveComparison()
                .isEqualTo(article);
        verify(articlePort).findById(articleId);
    }

    @DisplayName("존재하지 않는 글 ID로 조회를 시도하면 에러가 발생한다")
    @Test
    void checkFindNonExistArticleByIdThrowsException() {
        // given
        int articleIdThatDoesNotExist = 123;
        given(articlePort.findById(articleIdThatDoesNotExist))
                .willReturn(Optional.empty());

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> articleService.findById(articleIdThatDoesNotExist));

        //then
        assertThat(exception.getMessage())
                .isEqualTo("잘못 된 index 입니다");
        verify(articlePort).findById(articleIdThatDoesNotExist);
    }

    @DisplayName("모든 글 목록을 확인할 수 있다")
    @Test
    void checkFindAllArticleList() {
        // given
        List<Article> expectedArticles = List.of(
                new Article(0, "윤이진", LocalDateTime.of(2022, 1, 12, 16, 30), "Hello", "World"),
                new Article(1, "윤이진2", LocalDateTime.of(2022, 1, 12, 16, 30), "Hello2", "World2")
        );
        given(articlePort.findAll())
                .willReturn(expectedArticles);

        // when
        List<Article> articleList = articleService.findAll();

        //then
        assertThat(articleList)
                .extracting("id", "writer", "title", "contents")
                .containsExactly(
                        tuple(0, "윤이진", "Hello", "World"),
                        tuple(1, "윤이진2", "Hello2", "World2")
                );
        verify(articlePort).findAll();
    }

}