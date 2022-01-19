package com.kakao.cafe.article.repo;

import com.kakao.cafe.article.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcArticleRepository implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ArticleRowMapper mapper;

    public JdbcArticleRepository(JdbcTemplate jdbcTemplate, ArticleRowMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public long save(Article target) {
        beforeSave(target);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO `ARTICLE`(CREATED_AT, UPDATED_AT, TITLE, CONTENT) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setObject(1, target.getCreatedAt());
                    ps.setObject(2, target.getUpdatedAt());
                    ps.setString(3, target.getTitle());
                    ps.setString(4, target.getContent());
                    return ps;
                },
                keyHolder
        );
        if (keyHolder.getKey() == null) {
            throw new IllegalStateException("If approached here, there is a database problem.");
        }
        return keyHolder.getKey().longValue();
    }

    @Override
    public Article fetch(long id) {
        String query = "SELECT * FROM `ARTICLE` WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, mapper, id);
    }

    @Override
    public List<Article> fetchAll() {
        String query = "SELECT * FROM `ARTICLE`";
        return jdbcTemplate.query(query, mapper);
    }
}
