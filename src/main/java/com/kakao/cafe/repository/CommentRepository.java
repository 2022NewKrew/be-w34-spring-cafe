package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.dto.comment.CommentViewDto;
import com.kakao.cafe.rowmapper.CommentRowMapper;
import lombok.RequiredArgsConstructor;
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
public class CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    private final CommentRowMapper commentRowMapper;

    public Optional<Comment> findCommentByUserIdAndPostId(Long userId, Long postId) {
        String sql = "SELECT * FROM `COMMENT` " +
                "WHERE (user_id = ?) " +
                "AND (post_id = ?)";
        Comment comment = jdbcTemplate.queryForObject(sql, commentRowMapper.getCommentMapper(), userId, postId);
        return Optional.ofNullable(comment);
    }

    public List<CommentViewDto> getCommentViewDtoListByPostId(Long postId) {
        String sql = "SELECT c.contents, u.nick_name AS u_nick_name, c.created_at FROM `COMMENT` AS c " +
                "INNER JOIN `USER` AS u " +
                "INNER JOIN `POST` AS p " +
                "WHERE p.id = ?";
        return jdbcTemplate.query(sql, commentRowMapper.getCommentViewDtoMapper());
    }

    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            return add(comment);
        }
        return update(comment);
    }

    private Comment add(Comment comment) {
        String sql = "INSERT INTO `COMMENT` (contents, created_at, user_id, post_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, comment.getContents());
            ps.setTimestamp(2, Timestamp.valueOf(comment.getCreatedAt().toLocalDateTime()));
            ps.setLong(3, comment.getUserId());
            ps.setLong(4, comment.getPostId());
            return ps;
        }, keyHolder);
        comment.setId(keyHolder.getKey().longValue());
        return comment;
    }

    private Comment update(Comment comment) {
        String sql = "UPDATE `COMMENT` SET contents = ? WHERE id = ?";
        jdbcTemplate.update(sql, comment.getContents(), comment.getId());
        return comment;
    }

    public int deleteByCommentIdAndUserId(Long commentId, Long userId) {
        String sql = "DELETE FROM `COMMENT` " +
                "WHERE (id = ?) " +
                "AND (user_id = ?)";
        return jdbcTemplate.update(sql, commentId, userId);
    }
}
