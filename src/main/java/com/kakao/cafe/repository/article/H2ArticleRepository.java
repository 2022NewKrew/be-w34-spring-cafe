package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;

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
        String title = article.getTitle();
        String content = article.getContent();
        return jdbcTemplate.update(
                "INSERT INTO ARTICLES(TITLE, CONTENT) VALUES (?, ?)", title, content
        );
    }

    @Override
    public Article findById(int articleId) {
        return DataAccessUtils.singleResult(jdbcTemplate.query(
                "SELECT ID, TITLE, CONTENT FROM ARTICLES WHERE ID = ?", rowMapper , articleId
        ));
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(
                "SELECT ID, TITLE, CONTENT FROM ARTICLES", rowMapper
        );
    }
}
