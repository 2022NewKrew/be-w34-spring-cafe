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
    private static final String SQL_FOR_SAVE = "INSERT INTO posts(writer_id, title, content, created_at) VALUES(?, ?, ?, ?)";
    private static final String SQL_FOR_FIND_ALL = "SELECT posts.*, users.nickname FROM posts INNER JOIN users ON posts.writer_Id = users.id WHERE posts.deleted = 0";
    private static final String SQL_FOR_FIND_BY_ID = "SELECT posts.*, users.nickname FROM posts INNER JOIN users ON posts.writer_Id = users.id WHERE posts.id = ?";
    private static final String SQL_FOR_UPDATE = "UPDATE posts SET title = ?, content = ?, updated_at = ? WHERE id = ?";
    private static final String SQL_FOR_UPDATE_DELETED_BY_ID = "UPDATE posts SET deleted = 1, updated_at = ? WHERE id = ?";
    private static final String SQL_FOR_DELETE_BY_ID = "DELETE FROM posts WHERE id = ?";
    private static final String SQL_FOR_DELETE_ALL = "DELETE FROM posts";

    private final JdbcTemplate template;

    @Override
    public void save(Post post) {
        template.update(SQL_FOR_SAVE, post.getWriterId(), post.getTitle(), post.getContent(), post.getCreatedAt());
    }

    @Override
    public List<Post> findAll() {
        return template.query(SQL_FOR_FIND_ALL, postRowMapper());
    }

    @Override
    public Optional<Post> findById(long id) {
        return template.query(SQL_FOR_FIND_BY_ID, postRowMapper(), id).stream().findFirst();
    }

    @Override
    public void update(Post post) {
        template.update(SQL_FOR_UPDATE, post.getTitle(), post.getContent(), post.getUpdatedAt(), post.getId());
    }

    @Override
    public void updateDeletedById(long id) {
        template.update(SQL_FOR_UPDATE_DELETED_BY_ID, LocalDateTime.now(), id);
    }

    @Override
    public void deleteById(long id) {
        template.update(SQL_FOR_DELETE_BY_ID, id);
    }

    @Override
    public void deleteAll() {
        template.update(SQL_FOR_DELETE_ALL);
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> Post.builder()
                .id(rs.getLong("id"))
                .writerId(rs.getLong("writer_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .createdAt(getLocalDateTime(rs, "created_at"))
                .updatedAt(getLocalDateTime(rs, "updated_at"))
                .deleted(rs.getBoolean("deleted"))
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
