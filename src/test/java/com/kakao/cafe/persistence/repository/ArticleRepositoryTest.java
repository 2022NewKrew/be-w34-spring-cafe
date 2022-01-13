package com.kakao.cafe.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.persistence.model.Article;
import com.kakao.cafe.persistence.model.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql({"classpath:jdbc/schema.sql", "classpath:jdbc/test-data.sql"})
class ArticleRepositoryTest {

    private ArticleRepository articleRepository;

    @BeforeEach
    void initTest(@Autowired JdbcTemplate jdbcTemplate) {
        articleRepository = new ArticleRepositoryImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("Article Repository 저장 후 조회 테스트")
    void saveAndFindAll() {
        // Given
        Article article1 = Article.of("uid", "title1", "body1");
        Article article2 = Article.of("uid", "title2", "body2");
        Article article3 = Article.of("uid", "title3", "body3");

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

        // When
        List<Article> articles = articleRepository.findAllArticles();

        // Then
        assertThat(articles.size())
            .isEqualTo(3);
    }

    @Test
    @DisplayName("Article Repository 저장 후 특정 ID 조회 테스트")
    void findArticleById() {
        // Given
        Article article = Article.of("uid", "title", "body");

        articleRepository.save(article);

        // When
        Optional<Article> existArticle = articleRepository.findArticleById(1L);
        Optional<Article> notExistArticle = articleRepository.findArticleById(2L);

        // Then
        assertThat(existArticle)
            .isPresent();
        assertThat(notExistArticle)
            .isEmpty();
    }
}