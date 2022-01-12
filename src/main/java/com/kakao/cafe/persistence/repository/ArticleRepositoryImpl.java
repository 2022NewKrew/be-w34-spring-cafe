package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.Article;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Article article) {
        String sql = "INSERT INTO ARTICLE (uid, title, body) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getUid(), article.getTitle(), article.getBody());
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
        return Article.of(rs.getLong("id"),
            rs.getString("uid"),
            rs.getString("title"),
            rs.getString("body"),
            rs.getTimestamp("created_at").toLocalDateTime());
    }
}
