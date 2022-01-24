package com.kakao.cafe.article.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.common.exception.ForbiddenException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    private final Article article = Article.builder()
        .id(1L)
        .title("title")
        .body("body body body")
        .createdAt(LocalDateTime.now())
        .viewCount(0)
        .authorId(1L)
        .build();

    @Mock
    private ArticleRepository articleRepository;

    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        articleService = new ArticleService(articleRepository);
    }

    @DisplayName("게시글 상세 조회 - 해당 게시글이 존재하지 않으면 예외 발생")
    @Test
    void getSingleArticle_NotExists_Exception() {
        given(articleRepository.increaseViewCount(anyLong())).willReturn(Boolean.TRUE);
        given(articleRepository.findSingleArticle(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.getSingleArticle(article.getId()))
            .isInstanceOf(ArticleNotFoundException.class)
            .hasMessage("No Resource Error: 해당 게시글이 존재하지 않습니다.");
    }

    @DisplayName("게시글 수정 - 해당 게시글이 존재하지 않으면 예외 발생")
    @Test
    void update_NotExists_Exception() {
        given(articleRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.update(1L, article))
            .isInstanceOf(ArticleNotFoundException.class)
            .hasMessage("No Resource Error: 해당 게시글이 존재하지 않습니다.");
    }

    @DisplayName("게시글 수정 - 본인이 게시한 글이 아니면 예외 발생")
    @Test
    void update_NotMine_Exception() {
        Article other = Article.builder()
            .id(2L)
            .title("test")
            .body("body body body")
            .authorId(1L)
            .build();
        given(articleRepository.findById(anyLong())).willReturn(Optional.of(article));

        assertThatThrownBy(() -> articleService.update(2L, other))
            .isInstanceOf(ForbiddenException.class)
            .hasMessage("Forbidden Error");
    }

    @DisplayName("게시글 삭제 - 해당 게시글이 존재하지 않으면 예외 발생")
    @Test
    void delete_NotExists_Exception() {
        given(articleRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.delete(1L, 2L))
            .isInstanceOf(ArticleNotFoundException.class)
            .hasMessage("No Resource Error: 해당 게시글이 존재하지 않습니다.");
    }

    @DisplayName("게시글 삭제 - 본인이 게시한 글이 아니면 예외 발생")
    @Test
    void delete_NotMine_Exception() {
        given(articleRepository.findById(anyLong())).willReturn(Optional.of(article));

        assertThatThrownBy(() -> articleService.delete(2L, article.getId()))
            .isInstanceOf(ForbiddenException.class)
            .hasMessage("Forbidden Error");
    }
}