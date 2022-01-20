package com.kakao.cafe.comment.adapter.out.persistence;

import com.kakao.cafe.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentJdbcRepository implements CommentRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Comment create(Comment comment) {
        String sql = "insert into comment (content, created_at, question_post_id, user_account_id) values(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"comment_id"});
            ps.setString(1, comment.getContent());
            ps.setTimestamp(2, Timestamp.valueOf(comment.getCreatedAt()));
            ps.setLong(3, comment.getQuestionPostId());
            ps.setLong(4, comment.getUserAccountId());
            return ps;
        }, keyHolder);

        return Comment.builder()
                .commentId((Long) keyHolder.getKey())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .questionPostId(comment.getQuestionPostId())
                .userAccountId(comment.getUserAccountId())
                .build();
    }

    @Override
    public List<Comment> findByQuestionPostId(Long questionPostId) {
        String sql = "select * from comment where question_post_id = ?";

        return jdbcTemplate.query(
                sql,
                (result, rowNum) -> Comment.builder()
                        .commentId(result.getLong("comment_id"))
                        .content(result.getString("content"))
                        .createdAt(result.getTimestamp("created_at").toLocalDateTime())
                        .questionPostId(result.getLong("question_post_id"))
                        .userAccountId(result.getLong("user_account_id"))
                        .build(),
                questionPostId
        );
    }

    @Override
    public Optional<Comment> findById(Long id) {
        String sql = "select * from comment where comment_id = ?";

        try {
            Comment comment = jdbcTemplate.queryForObject(
                    sql,
                    (result, row) -> Comment.builder()
                            .commentId(result.getLong("comment_id"))
                            .createdAt(result.getTimestamp("created_at").toLocalDateTime())
                            .content(result.getString("content"))
                            .questionPostId(result.getLong("question_post_id"))
                            .userAccountId(result.getLong("user_account_id"))
                            .build(),
                    id);

            return Optional.of(comment);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long commentId) {
        String sql = "delete from comment where comment_id = ?";
        jdbcTemplate.update(sql, commentId);
    }
}
