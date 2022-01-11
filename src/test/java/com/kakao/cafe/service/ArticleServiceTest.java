package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.ArticleRegisterRequestDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    ArticleService articleService;
    @Mock
    ArticleRepository articleRepository;

    @Test
    @DisplayName("아이디로 게시글 조회")
    void testOfFindById() {
        ArticleRegisterRequestDto articleRegisterRequestDto = ArticleRegisterRequestDto.builder()
                .build();
        BDDMockito.given(articleRepository.findById(1L)).willReturn(Optional.of(Article.builder()
                .id(1L)
                .build()));
        articleService.register(articleRegisterRequestDto);
        Article article = articleService.findById(1L);
        Assertions.assertThat(article.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("존재하지 않는 아이디로 게시글 조회")
    void testOfFindNonExistentArticle() {
        BDDMockito.given(articleRepository.findById(1L)).willReturn(Optional.empty());
        assertThatThrownBy(() -> articleService.findById(1L)).isInstanceOf(ArticleNotFoundException.class);
    }

    @Test
    @DisplayName("게시글 전체 조회")
    void testOfFindAll() {
        Article article1 = Article.builder().build();
        Article article2 = Article.builder().build();
        Article article3 = Article.builder().build();
        BDDMockito.given(articleRepository.findAll()).willReturn(List.of(article1, article2, article3));
        List<Article> articles = articleService.findAll();
        assertThat(articles).contains(article1, article2, article3);
    }

    @Test
    @DisplayName("게시글 등록")
    void testOfRegister() {
        ArticleRegisterRequestDto articleRegisterRequestDto = ArticleRegisterRequestDto.builder()
                .build();
        articleService.register(articleRegisterRequestDto);
        BDDMockito.then(articleRepository).should().save(BDDMockito.any());
    }
}