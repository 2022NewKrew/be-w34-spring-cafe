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
        jdbcTemplate.update("INSERT INTO articles(writer, title, contents, deleted) VALUES(?,?,?,?)",
                article.getWriter(), article.getTitle(), article.getContents(), false);
    }

    public List<Article> getArticles() {
        return jdbcTemplate.query("SELECT * FROM articles WHERE deleted = ?", articleRowMapper(), false);
    }

    public Article getArticle(int index) {
        return jdbcTemplate.query("SELECT * FROM articles WHERE id = ?", articleRowMapper(), index)
                .stream().findFirst().orElse(null);
    }

    public void updateArticle(int index, Article article) {
        jdbcTemplate.update("UPDATE articles SET title=?,contents=? WHERE id=?",
                article.getTitle(), article.getContents(), index);
    }

    public void deleteArticle(int index) {
        jdbcTemplate.update("UPDATE articles SET deleted=? WHERE id=?", true, index);
    }

}
