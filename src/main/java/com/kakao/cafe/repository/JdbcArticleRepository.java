package com.kakao.cafe.repository;

import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.dto.ArticleResponseDto;
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
    public void save(ArticleRequestDto article) {
        String sql = "INSERT INTO ARTICLE (author, title, content, createdAt) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getAuthor(), article.getTitle(), article.getContent(), LocalDateTime.now());
    }

    @Override
    public Optional<ArticleResponseDto> findById(Long id) {
        String sql = "SELECT * FROM ARTICLE WHERE id = ?";
        return jdbcTemplate.query(sql, this::articleRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public List<ArticleResponseDto> findAll() {
        String sql = "SELECT * FROM ARTICLE";
        return jdbcTemplate.query(sql, this::articleRowMapper);
    }

    private ArticleResponseDto articleRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return ArticleResponseDto.of(rs.getLong("id"),
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("createdAt").toLocalDateTime());
    }
}
