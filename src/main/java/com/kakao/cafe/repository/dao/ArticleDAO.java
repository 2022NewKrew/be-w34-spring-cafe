package com.kakao.cafe.repository.dao;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ArticleDAO {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Article article) {
        String sql = "INSERT INTO ARTICLES (TITLE,CONTENT,CREATE_USER_ID,CREATE_DATE,VIEWS) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getCreateUserId(), article.getCreateDate(), article.getViews());
    }

    public Article findById(long id) {
        String sql = "SELECT * FROM ARTICLES WHERE ID=?";
        return jdbcTemplate.queryForObject(sql, articleRowMapper(), id);
    }

    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> Article.newInstance(rs.getLong("id"), rs.getString("title"), rs.getString("content"), rs.getString("create_user_id"), rs.getString("create_date"), rs.getInt("views"));
    }
}
