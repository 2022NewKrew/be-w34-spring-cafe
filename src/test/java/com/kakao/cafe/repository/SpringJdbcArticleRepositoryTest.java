package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Sql("classpath:script.sql")
class SpringJdbcArticleRepositoryTest {

    SpringJdbcArticleRepository springJdbcArticleRepository;
    SpringJdbcUserRepository springJdbcUserRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        UserMapper userMapper = new UserMapper();
        springJdbcUserRepository = new SpringJdbcUserRepository(jdbcTemplate, userMapper);
        springJdbcArticleRepository = new SpringJdbcArticleRepository(jdbcTemplate, new ArticleMapper(userMapper));
    }

    @Test
    @DisplayName("전체 조회")
    void testOfFindAll() {
        User user1 = User.builder()
                .userId("1")
                .password("1")
                .name("1")
                .email("1")
                .build();
        springJdbcUserRepository.save(user1);
        Article article1 = Article.builder()
                .writer(user1)
                .title("123")
                .contents("125125")
                .build();
        Article article2 = Article.builder()
                .writer(user1)
                .title("123")
                .contents("125125")
                .build();
        Article article3 = Article.builder()
                .writer(user1)
                .title("123")
                .contents("125125")
                .build();
        springJdbcArticleRepository.save(article1);
        springJdbcArticleRepository.save(article2);
        springJdbcArticleRepository.save(article3);

        List<Article> foundArticles = springJdbcArticleRepository.findAll();

        assertThat(foundArticles.size()).isEqualTo(3);
    }

}