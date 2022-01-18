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
        String sql = "INSERT INTO posts(writer_id, title, content, createdAt) VALUES(?, ?, ?, ?)";
        template.update(sql, post.getWriterId(), post.getTitle(), post.getContent(), post.getCreatedAt());
    }

    @Override
    public List<Post> findAll() {
        String sql = "SELECT posts.*, users.nickname FROM posts INNER JOIN users ON posts.writer_Id = users.id";
        return template.query(sql, postRowMapper());
    }

    @Override
    public Optional<Post> findById(long id) {
        String sql = "SELECT posts.*, users.nickname FROM posts INNER JOIN users ON posts.writer_Id = users.id WHERE posts.id = ?";
        return template.query(sql, postRowMapper(), id).stream().findFirst();
    }

    @Override
    public void update(Post post) {
        String sql = "UPDATE posts SET title = ?, content = ?, updatedAt = ? WHERE id = ?";
        template.update(sql, post.getTitle(), post.getContent(), post.getUpdatedAt(), post.getId());
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM posts";
        template.update(sql);
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> Post.builder()
                .id(rs.getLong("id"))
                .writerId(rs.getLong("writer_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .createdAt(getLocalDateTime(rs, "createdAt"))
                .updatedAt(getLocalDateTime(rs, "updatedAt"))
                .writerNickname(rs.getString("nickname"))
                .build();
    }

    private LocalDateTime getLocalDateTime(ResultSet rs, String columnLabel) throws SQLException {
        if (rs.getTimestamp(columnLabel) == null) {
            return null;
        }
        return rs.getTimestamp(columnLabel).toLocalDateTime();
    }
}
