package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

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
    public Article findById(Long id) {
        String sql = "SELECT * FROM articles where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, articleRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("해당하는 게시글이 존재하지 않습니다.");
        }
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(rs.getLong("id"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"));
    }
}
