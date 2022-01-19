package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.exception.custom.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Article article) {
        jdbcTemplate.update("INSERT INTO article(title, content, create_time) VALUES(?, ?, ?)"
                , article.getTitle(), article.getContent(), LocalDateTime.now());
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT id, title, content, create_time FROM article", articleRowMapper());
    }

    @Override
    public Article findById(Long id) {
        List<Article> query = jdbcTemplate.query("SELECT id, title, content, create_time FROM article WHERE id = ?", articleRowMapper(), id);
        if(query.isEmpty()) throw new NotFoundException();
        return query.get(0);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            return Article.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .createTime(rs.getTimestamp("create_time").toLocalDateTime())
                    .build();
        };
    }
}
