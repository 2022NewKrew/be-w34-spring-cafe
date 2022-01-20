package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcArticleRepository implements ArticleRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(ArticleRequestDTO article) {
        String sql = "INSERT INTO ARTICLE (author, title, content, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getAuthor(), article.getTitle(), article.getContent(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM ARTICLE WHERE id = ?";
        return jdbcTemplate.query(sql, this::articleRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLE";
        return jdbcTemplate.query(sql, this::articleRowMapper);
    }

    @Override
    public void update(Long id, ArticleRequestDTO article) {
        String sql = "UPDATE ARTICLE SET title = ?, content = ?, updatedAt = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), LocalDateTime.now(), id);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ARTICLE WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Article articleRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return Article.builder()
                .id(rs.getLong("id"))
                .author(rs.getString("author"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updatedAt").toLocalDateTime())
                .build();
    }
}
