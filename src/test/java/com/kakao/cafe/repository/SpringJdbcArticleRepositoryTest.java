package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.article.SpringJdbcArticleRepository;
import com.kakao.cafe.repository.user.SpringJdbcUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("회원 수정")
    void testOfUpdate() {
        User user1 = User.builder()
                .userId("1")
                .password("1")
                .name("1")
                .email("1")
                .build();
        springJdbcUserRepository.save(user1);

        User foundUser = springJdbcUserRepository.findByUserId(user1.getUserId()).get();
        foundUser.update("김남현", "123", "leaf.hyeon@kakaocorp.com");
        springJdbcUserRepository.update(foundUser);
        User updateUser = springJdbcUserRepository.findByUserId(user1.getUserId()).get();
        assertThat(updateUser.getName()).isEqualTo("김남현");
        assertThat(updateUser.getPassword()).isEqualTo("123");
        assertThat(updateUser.getEmail()).isEqualTo("leaf.hyeon@kakaocorp.com");

    }
}