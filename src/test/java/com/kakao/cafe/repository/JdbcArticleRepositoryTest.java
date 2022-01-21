package com.kakao.cafe.repository;

import com.kakao.cafe.config.SecurityConfig;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.util.ArticleMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class JdbcArticleRepositoryTest {

    private final String title1 = "testTitle1";
    private final String content1 = "testContent1";
    private final String title2 = "testTitle2";
    private final String content2 = "testContent2";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ArticleMapper articleMapper;

    @Test
    @DisplayName("[성공] JdbcArticleRepository 클래스 생성")
    void JdbcArticleRepository() {
        new JdbcArticleRepository(jdbcTemplate, articleMapper);
    }

    @Test
    @DisplayName("[성공] JdbcArticleRepository 게시글 생성")
    void create() {
        // given
        JdbcArticleRepository jdbcArticleRepository = new JdbcArticleRepository(jdbcTemplate, articleMapper);
        Article article = new Article(1, title1, content1);

        // when * then
        jdbcArticleRepository.create(article);
    }

    @Test
    @DisplayName("[성공] JdbcArticleRepository 게시 전체 조회")
    void readAll() {
        // given
        JdbcArticleRepository jdbcArticleRepository = new JdbcArticleRepository(jdbcTemplate, articleMapper);
        Article article1 = new Article(1, title1, content1);
        Article article2 = new Article(2, title2, content2);

        // when
        jdbcArticleRepository.create(article1);
        jdbcArticleRepository.create(article2);

        // then
        List<Article> articles = jdbcArticleRepository.readAll();
        Assertions.assertEquals(3, articles.size());
    }

    @Test
    @DisplayName("[성공] JdbcArticleRepository 게시글 단일 조회")
    void readById() {
        // given
        JdbcArticleRepository jdbcArticleRepository = new JdbcArticleRepository(jdbcTemplate, articleMapper);
        Article article1 = new Article(1, title1, content1);
        Article article2 = new Article(2, title2, content2);

        // when
        jdbcArticleRepository.create(article1);
        jdbcArticleRepository.create(article2);

        // then
        Optional<Article> answer = jdbcArticleRepository.readById(2);
        Assertions.assertEquals("testTitle1",
                answer.orElseThrow(() -> new RuntimeException("userId가 null입니다")).getTitle());
    }
}