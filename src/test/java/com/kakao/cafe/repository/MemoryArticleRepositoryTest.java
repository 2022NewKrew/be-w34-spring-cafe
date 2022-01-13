package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.article.MemoryArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryArticleRepositoryTest {

    MemoryArticleRepository memoryArticleRepository;

    @BeforeEach
    void setUp() {
        memoryArticleRepository = new MemoryArticleRepository();
    }

    @Test
    @DisplayName("아이디로 게시글 조회")
    void testOfFindById() {
        Article article = Article.builder().build();
        memoryArticleRepository.save(article);
        Article foundArticle = memoryArticleRepository.findById(1L).get();

        assertThat(foundArticle).isEqualTo(article);
    }

    @Test
    @DisplayName("전체 조회")
    void testOfFindAll() {
        Article article1 = Article.builder().build();
        Article article2 = Article.builder().build();
        Article article3 = Article.builder().build();
        memoryArticleRepository.save(article1);
        memoryArticleRepository.save(article2);
        memoryArticleRepository.save(article3);

        List<Article> foundArticles = memoryArticleRepository.findAll();

        assertThat(foundArticles.size()).isEqualTo(3);
        assertThat(foundArticles).contains(article1, article2, article3);
    }
}