package com.kakao.cafe.dao;

import com.kakao.cafe.model.vo.ArticleVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ArticleVo> findAllArticle() {
        String sql = "SELECT * FROM articles";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    public ArticleVo filterArticleByIndex(int index) {
        String sql = "SELECT * FROM articles WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, articleRowMapper(), index);
    }

    public void writeArticle(ArticleVo article) {
        String sql = "INSERT INTO articles (writer, title, contents) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getWriter(), article.getTitle(), article.getContents());
    }

    public void updateArticle(int index, ArticleVo article) {
        String sql = "UPDATE articles SET writer = ?, title = ?, contents = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getWriter(), article.getTitle(), article.getContents(), index);
    }

    public void deleteArticle(int index) {
        String sql = "DELETE FROM articles WHERE id = ?";
        jdbcTemplate.update(sql, index);
    }

    private RowMapper<ArticleVo> articleRowMapper() {
        return (rs, rowNum) -> new ArticleVo(
                rs.getInt("id"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents")
        );
    }
}
