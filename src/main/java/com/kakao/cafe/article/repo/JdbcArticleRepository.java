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

    public JdbcArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Article target) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO `ARTICLE`(TITLE, CONTENT) VALUES (?, ?)";
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1, target.getTitle());
                    ps.setString(2, target.getContent());
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
        List<Article> articles = jdbcTemplate.query(query, new ArticleRowMapper(), id);
        return articles.size() == 0 ? null : articles.get(0);
    }

    @Override
    public List<Article> fetchAll() {
        String query = "SELECT * FROM `ARTICLE`";
        return jdbcTemplate.query(query, new ArticleRowMapper());
    }
}
