package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplatesArticle {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplatesArticle(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Article article) {
        String sql = "INSERT INTO ARTICLES (writer, title, contents, created_at) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                article.getWriter(), article.getTitle(), article.getContents(), article.getTime());
    }

    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    public Article findOneById(int id) {
        String sql = "SELECT * FROM ARTICLES WHERE id = ?";
        List<Article> result = jdbcTemplate.query(sql, articleRowMapper(), id);
        return result.get(0);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article(
                    rs.getString("writer"),
                    rs.getString("title"),
                    rs.getString("contents")
            );
            article.setIndex(rs.getInt("id"));
            return article;
        };
    }
}
