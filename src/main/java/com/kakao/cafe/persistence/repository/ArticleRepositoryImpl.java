package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.Article;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Article article) {
        String sql = "INSERT INTO ARTICLE (uid, title, body, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getUid(), article.getTitle(), article.getBody(),
            article.getCreatedAt());
    }

    @Override
    public List<Article> findAllArticles() {
        String sql = "SELECT * FROM ARTICLE";
        return jdbcTemplate.query(sql, this::articleRowMapper);
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        String sql = "SELECT * FROM ARTICLE WHERE id = ?";
        return jdbcTemplate.query(sql, this::articleRowMapper, id).stream()
            .findFirst();
    }

    private Article articleRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return Article.builder()
            .id(rs.getLong("id"))
            .uid(rs.getString("uid"))
            .title(rs.getString("title"))
            .body(rs.getString("body"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
            .build();
    }
}
