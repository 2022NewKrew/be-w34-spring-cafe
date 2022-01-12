package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private static Long idNumber = 0L;

    public JdbcArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long generateId() {
        return ++idNumber;
    }

    @Override
    public void create(Article article) {
        String sql = "INSERT INTO articles VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getId(), article.getWriter(), article.getTitle(), article.getContents());
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM articles";
        return jdbcTemplate.query(sql, articleRowMapper());

    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM articles where id = ?";
        List<Article> result = jdbcTemplate.query(sql, articleRowMapper(), id);
        return result.stream().findAny();

    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(rs.getLong("id"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"));
    }
}
