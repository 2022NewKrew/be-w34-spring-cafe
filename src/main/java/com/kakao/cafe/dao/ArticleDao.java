package com.kakao.cafe.dao;

import com.kakao.cafe.vo.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents")
        );
    }

    public void addArticle(Article article) {
        jdbcTemplate.update("INSERT INTO articles(writer, title, contents) VALUES(?,?,?)",
                article.getWriter(), article.getTitle(), article.getContents());
    }

    public List<Article> getArticles() {
        return jdbcTemplate.query("SELECT * FROM articles", articleRowMapper());
    }

    public Article getArticle(int index) {
        return jdbcTemplate.query("SELECT * FROM articles WHERE id = ?", articleRowMapper(), index)
                .stream().findFirst().orElse(null);
    }

}
