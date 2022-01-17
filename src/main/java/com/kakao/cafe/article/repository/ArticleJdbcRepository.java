package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleJdbcRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ArticleJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(Article article) {
        String sql = "INSERT INTO ARTICLES(WRITER_ID, WRITE_TIME, TITLE, CONTENTS) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getWriterId().getUserId(), article.getWriteTime(), article.getTitle().getTitle(), article.getContents().getContents());
        return article;

    }

    @Override
    public Optional<Article> findByArticleId(Long articleId) {
        try {
            String sql = "SELECT ARTICLE_ID, WRITER_ID, WRITE_TIME, TITLE, CONTENTS FROM ARTICLES WHERE ARTICLE_ID = ?";
            Article article = jdbcTemplate.queryForObject(sql, new ArticleMapper(), articleId);
            return Optional.ofNullable(article);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT ARTICLE_ID, WRITER_ID, WRITE_TIME, TITLE, CONTENTS FROM ARTICLES";
        return jdbcTemplate.query(sql, new ArticleMapper());
    }
}
