package com.kakao.cafe.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostJdbcRepositoryImpl implements PostRepository {
    private final JdbcTemplate template;

    @Override
    public void save(Post post) {
        String sql = "INSERT INTO posts(writer, title, content, createdAt) VALUES(?, ?, ?, ?)";
        template.update(sql, post.getWriter(), post.getTitle(), post.getContent(), post.getCreatedAt());
    }

    @Override
    public List<Post> findAll() {
        String sql = "SELECT * FROM posts";
        return template.query(sql, postRowMapper());
    }

    @Override
    public Optional<Post> findById(long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        return template.query(sql, postRowMapper(), id).stream().findFirst();
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM posts";
        template.update(sql);
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> Post.builder()
                .id(rs.getLong("id"))
                .writer(rs.getString("writer"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .createdAt(getLocalDateTime(rs, "createdAt"))
                .build();
    }

    private LocalDateTime getLocalDateTime(ResultSet rs, String columnLabel) throws SQLException {
        if (rs.getTimestamp(columnLabel) == null) {
            return null;
        }
        return rs.getTimestamp(columnLabel).toLocalDateTime();
    }
}
