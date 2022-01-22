package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ArticleRepositoryImplH2 implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    public ArticleRepositoryImplH2(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createArticle(Article article) {
        jdbcTemplate.update(
            "INSERT INTO ARTICLES (TITLE, WRITER, CONTENTS) VALUES ( ?, ?, ? )",
            article.getTitle(), article.getWriter(), article.getContents()
        );
    }

    @Override
    public List<Article> findAllArticles() {
        return jdbcTemplate.query(
            "SELECT * FROM ARTICLES",
            articleRowMapper()
        );
    }

    @Override
    public boolean isArticleIdUsed(Integer aid) {
        List<Article> result = jdbcTemplate.query(
            "SELECT * FROM ARTICLES WHERE ID=?",
            articleRowMapper(), aid
        );
        return !result.isEmpty();
    }

    @Override
    public Article findArticleByArticleId(Integer aid) {
        List<Article> result = jdbcTemplate.query(
            "SELECT * FROM ARTICLES WHERE ID=?",
            articleRowMapper(), aid
        );
        return result.get(0);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, count) -> new Article(
            rs.getInt("ID"),
            rs.getString("TITLE"),
            rs.getString("WRITER"),
            rs.getString("CONTENTS"),
            rs.getTimestamp("CREATED_AT").toLocalDateTime(),
            rs.getTimestamp("UPDATED_AT").toLocalDateTime());
    }
}
