package com.kakao.cafe.domain.comment;

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
public class CommentRepositoryImpl implements CommentRepository {

    private static final String SQL_FOR_SAVE = "INSERT INTO comments(post_id, writer_id, content, created_at) VALUES (?, ?, ?, ?)";
    private static final String SQL_FOR_FIND_BY_ID = "" +
            "SELECT " +
            "   * " +
            "FROM " +
            "   comments " +
            "WHERE " +
            "   id = ?";
    private static final String SQL_FOR_FIND_BY_POST_ID = "" +
            "SELECT " +
            "   comments.*, " +
            "   users.nickname " +
            "FROM " +
            "   comments " +
            "   INNER JOIN users ON comments.writer_id = users.id " +
            "WHERE " +
            "   comments.post_id = ?";
    private static final String SQL_FOR_DELETE_BY_ID = "" +
            "DELETE FROM " +
            "   comments " +
            "WHERE " +
            "   id = ?";

    private final JdbcTemplate template;

    @Override
    public void save(Comment comment) {
        template.update(SQL_FOR_SAVE, comment.getPostId(), comment.getWriterId(), comment.getContent(), comment.getCreatedAt());
    }

    @Override
    public Optional<Comment> findById(long id) {
        return template.query(SQL_FOR_FIND_BY_ID, commentRowMapper(), id).stream().findFirst();
    }

    @Override
    public List<Comment> findByPostId(long postId) {
        return template.query(SQL_FOR_FIND_BY_POST_ID, commentUserRowMapper(), postId);
    }

    @Override
    public void deleteById(long id) {
        template.update(SQL_FOR_DELETE_BY_ID, id);
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> Comment.builder()
                .id(rs.getLong("id"))
                .postId(rs.getLong("post_id"))
                .writerId(rs.getLong("writer_id"))
                .content(rs.getString("content"))
                .createdAt(getLocalDateTime(rs, "created_at"))
                .updatedAt(getLocalDateTime(rs, "updated_at"))
                .deleted(rs.getBoolean("deleted"))
                .build();
    }

    private RowMapper<Comment> commentUserRowMapper() {
        return (rs, rowNum) -> Comment.builder()
                .id(rs.getLong("id"))
                .postId(rs.getLong("post_id"))
                .writerId(rs.getLong("writer_id"))
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
