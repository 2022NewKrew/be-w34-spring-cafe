package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.article.mapper.ArticleRowMapper;
import com.kakao.cafe.repository.user.JdbcUserRepository;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.repository.user.mapper.UserRowMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql(scripts = {"classpath:/sql/schema.sql", "classpath:/sql/insert.sql"})
class JdbcArticleRepositoryTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcArticleRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    @DisplayName("유저가 게시글을 작성한다.")
    void insert() {
        // given
        ArticleRepository articleRepository = new JdbcArticleRepository(jdbcTemplate, new ArticleRowMapper());
        User writer = User.of("jeuslhg", "1234", "이희관", "jeuslhg@gmail.com");

        // when
        Article article = Article.of(writer, "제목1", "안녕하세요.");
        Long articleId = articleRepository.insert(article);

        // then
        assertThat(articleId).isGreaterThan(0L);
    }

    @Test
    @DisplayName("유저가 게시글을 삭제한다.")
    void delete() {
        // given
        // when
        // then
    }
}