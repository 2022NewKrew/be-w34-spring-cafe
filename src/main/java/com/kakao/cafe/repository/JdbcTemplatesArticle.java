package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class JdbcTemplatesArticle {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplatesArticle(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO ARTICLES (writer, title, contents, created_at) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update((con) -> {
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getTitle());
            pstmt.setString(3, article.getContents());
            pstmt.setString(4, article.getTime());
            return pstmt;
        }, keyHolder);

        article.setId(keyHolder.getKey().longValue());
    }

    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES";
        return jdbcTemplate.query(sql, articleRowMapper());
    }

    public Article findOneById(long id) {
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
            article.setId(rs.getLong("id"));
            return article;
        };
    }
}
