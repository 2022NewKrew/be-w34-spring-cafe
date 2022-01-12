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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
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

    @DisplayName("모든 글 목록을 확인할 수 있다")
    @Test
    void checkReadAllArticleList() {
        // given
        List<Article> expectedArticles = List.of(
                new Article(0, "윤이진", LocalDateTime.of(2022, 1, 12, 16, 30), "Hello", "World"),
                new Article(1, "윤이진2", LocalDateTime.of(2022, 1, 12, 16, 30), "Hello2", "World2")
        );
        given(articlePort.findAll())
                .willReturn(expectedArticles);

        // when
        List<Article> articleList = articleService.readAll();

        //then
        assertThat(articleList)
                .extracting("index", "writer", "title", "contents")
                .containsExactly(
                        tuple(0, "윤이진", "Hello", "World"),
                        tuple(1, "윤이진2", "Hello2", "World2")
                );
        verify(articlePort).findAll();
    }

}