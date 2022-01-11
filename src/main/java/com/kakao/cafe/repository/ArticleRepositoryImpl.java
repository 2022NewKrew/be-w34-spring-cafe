package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleRepository;
import com.kakao.cafe.repository.mapper.ArticleMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> rowMapper;

    public ArticleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new ArticleMapper();
    }

    @Override
    public Optional<Article> findById(Long id) {
        Article article = jdbcTemplate.queryForObject("SELECT * FROM ARTICLE WHERE ID = ?", rowMapper, id);
        return Optional.ofNullable(article);
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM ARTICLE", rowMapper);
    }

    @Override
    public Long save(Article article) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(
                            "INSERT INTO ARTICLE(author, title, content, created_at, updated_at) VALUES (?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, article.getAuthor());
            statement.setString(2, article.getTitle());
            statement.setString(3, article.getContent());
            statement.setTimestamp(4, Timestamp.valueOf(article.getCreatedAt()));
            statement.setTimestamp(5, Timestamp.valueOf(article.getUpdatedAt()));
            return statement;
        }, generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }
}
