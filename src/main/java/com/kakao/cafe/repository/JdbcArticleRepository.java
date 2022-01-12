package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Primary
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
        jdbcTemplate.update("INSERT INTO articles (title, content, users_id) VALUES (?, ?, ?)",
                article.getTitle().getValue(),
                article.getContent().getValue(),
                article.getWriter().getId().toString());
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM "
                + "articles INNER JOIN users USING(users_id)", rowMapper);
    }

    @Override
    public Optional<Article> findArticleById(UUID id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM "
                    + "articles INNER JOIN users USING(users_id) "
                    + "WHERE articles_id = ?", rowMapper, id.toString()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }
}
