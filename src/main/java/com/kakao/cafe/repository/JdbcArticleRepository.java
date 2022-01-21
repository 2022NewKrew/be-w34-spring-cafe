package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Article> rowMapper;

    public JdbcArticleRepository(JdbcTemplate jdbcTemplate, RowMapper<Article> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(Article article) {
        jdbcTemplate.update("INSERT INTO articles (title, content, users_id) VALUES (?, ?, UUID_TO_BIN(?))",
                article.getTitle().getValue(),
                article.getContent().getValue(),
                article.getWriter().getId().toString());
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT " +
                "BIN_TO_UUID(articles_id) as articles_id, " +
                "title, " +
                "content, " +
                "created_at, " +
                "view_count, " +
                "deleted, " +
                "BIN_TO_UUID(users_id) as users_id, " +
                "username, " +
                "password, " +
                "name, " +
                "email " +
                "FROM articles INNER JOIN users USING(users_id) "
                + "WHERE deleted = FALSE", rowMapper);
    }

    @Override
    public Optional<Article> findArticleById(UUID id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT " +
                    "BIN_TO_UUID(articles_id) as articles_id, " +
                    "title, " +
                    "content, " +
                    "created_at, " +
                    "view_count, " +
                    "deleted, " +
                    "BIN_TO_UUID(users_id) as users_id, " +
                    "username, " +
                    "password, " +
                    "name, " +
                    "email " +
                    "FROM articles INNER JOIN users USING(users_id) "
                    + "WHERE articles_id = UUID_TO_BIN(?) AND deleted = FALSE", rowMapper, id.toString()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void increaseViewCount(Article article) {
        jdbcTemplate.update("UPDATE articles SET view_count = view_count + 1 "
                        + "WHERE articles_id = UUID_TO_BIN(?)",
                article.getArticleId().toString());
    }

    @Override
    public void update(Article article) {
        jdbcTemplate.update("UPDATE articles SET title = ?, content = ? "
                + "WHERE articles_id = UUID_TO_BIN(?)",
                article.getTitle().getValue(),
                article.getContent().getValue(),
                article.getArticleId().toString());
    }

    @Override
    public void delete(Article article) {
        jdbcTemplate.update("UPDATE articles SET deleted = TRUE "
                        + "WHERE articles_id = UUID_TO_BIN(?)",
                article.getArticleId().toString());
    }
}
