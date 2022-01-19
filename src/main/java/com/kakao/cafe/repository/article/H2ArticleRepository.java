package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class H2ArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;
    private final ArticleRowMapper rowMapper;

    public H2ArticleRepository(JdbcTemplate jdbcTemplate) {
        this(jdbcTemplate, new ArticleRowMapper());
    }

    public H2ArticleRepository(JdbcTemplate jdbcTemplate, ArticleRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public int save(Article article) {
        String title = article.getTitle().getValue();
        String content = article.getContent().getValue();
        LocalDateTime now = LocalDateTime.now();
        return jdbcTemplate.update(
                "INSERT INTO ARTICLES(TITLE, CONTENT, CREATEDAT, MODIFIEDAT) VALUES (?, ?, ?, ?)", title, content, Timestamp.valueOf(now), Timestamp.valueOf(now)
        );
    }

    @Override
    public Article findById(int articleId) {
        return jdbcTemplate.queryForObject(
                "SELECT ID, TITLE, CONTENT, CREATEDAT, MODIFIEDAT FROM ARTICLES WHERE ID = ?", rowMapper , articleId
        );
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(
                "SELECT ID, TITLE, CONTENT, CREATEDAT, MODIFIEDAT FROM ARTICLES", rowMapper
        );
    }
}
