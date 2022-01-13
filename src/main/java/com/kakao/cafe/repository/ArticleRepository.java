package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepository implements MyRepository<Article, Long> {

    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper mapper;
    private final AtomicLong sequenceId = new AtomicLong();

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new ArticleMapper();
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "select a.id, a.title, a.description " +
                "from article a " +
                "where a.id = ?";

        try {
            Article article = jdbcTemplate.queryForObject(sql, mapper, id);
            return Optional.ofNullable(article);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Article> findAll() {
        String sql = "select a.id, a.title, a.description " +
                "from article a";

        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public void save(Article entity) {
        String sql = "insert into article values ( ?, ?, ? )";
        entity.setId(sequenceId.incrementAndGet());

        jdbcTemplate.update(
                sql,
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }

    @Override
    public void update(Article entity) {
        String sql = "update article " +
                "set title = ?, description = ? " +
                "where id = ?";

        jdbcTemplate.update(
                sql,
                entity.getTitle(),
                entity.getDescription(),
                entity.getId()
        );
    }

    private static class ArticleMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article(
                    rs.getString("title"),
                    rs.getString("description")
            );
            article.setId(rs.getLong("id"));
            return article;
        }
    }
}
