package com.kakao.cafe.thread.repository;

import com.kakao.cafe.thread.domain.Comment;
import com.kakao.cafe.thread.domain.ThreadStatus;
import com.kakao.cafe.thread.domain.ThreadType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;


public class SpringJdbcCommentRepository implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcCommentRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long add(Comment comment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO thread (parent_id, author_id, title, content, status, type) VALUES (?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, comment.getParentId());
            ps.setLong(2, comment.getAuthorId());
            ps.setString(3, comment.getTitle());
            ps.setString(4, comment.getContent());
            ps.setString(5, comment.getStatus());
            ps.setString(6, ThreadType.COMMENT.name());
            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Comment> getCommentsForPost(Long postId) {
        return jdbcTemplate.query("SELECT * FROM thread WHERE parent_id = ? AND status = ? AND type = ?", commentRowMapper(), postId, ThreadStatus.VALID.name(), ThreadType.COMMENT.name());
    }

    @Override
    public Optional<Comment> get(Long id) {
        return jdbcTemplate.query("SELECT * FROM thread WHERE id = ? AND status = ? AND type = ?", commentRowMapper(), id,
                                  ThreadStatus.VALID.name(), ThreadType.COMMENT.name()).stream().findAny();
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> Comment.builder()
                                   .id(rs.getLong("id"))
                                   .parentId(rs.getLong("parent_id"))
                                   .authorId(rs.getLong("author_id"))
                                   .title(rs.getString("title"))
                                   .content(rs.getString("content"))
                                   .status(rs.getString("status"))
                                   .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                   .lastModifiedAt(rs.getTimestamp("last_modified_at").toLocalDateTime())
                                   .build();
    }

    @Override
    public void update(Comment comment) {
        jdbcTemplate.update(
                "UPDATE thread SET content = ?, status = ? WHERE id = ? AND type = ?;",
                comment.getContent(), comment.getStatus(), comment.getId(), ThreadType.COMMENT.name());
    }
}
