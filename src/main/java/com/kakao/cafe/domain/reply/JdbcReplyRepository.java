package com.kakao.cafe.domain.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Reply reply) {
        String sql = "INSERT INTO `REPLY` (comment, userId, regDateTime, postId) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, reply.getComment(), reply.getUserId(), reply.getRegDateTime(), reply.getPostId());
    }

    @Override
    public List<Reply> findAll(Long postId) {
        String sql = "SELECT id, comment, userId, regDateTime, postId FROM `REPLY` WHERE postId=? AND isDeleted=0";
        return jdbcTemplate.query(sql, getReplyRowMapper(), postId);
    }

    @Override
    public Optional<Reply> findById(Long replyId) {
        String sql = "SELECT id, comment, userId, regDateTime, postId FROM `REPLY` WHERE id=? AND isDeleted=0";
        try {
            Reply reply = jdbcTemplate.queryForObject(sql, getReplyRowMapper(), replyId);
            return Optional.ofNullable(reply);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "UPDATE `REPLY` SET isDeleted=1 WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Reply> getReplyRowMapper() {
        return (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setId(rs.getLong("id"));
            reply.setComment(rs.getString("comment"));
            reply.setUserId(rs.getString("userId"));
            reply.setPostId(rs.getLong("postId"));
            reply.setRegDateTime(rs.getTimestamp("regDateTime").toLocalDateTime());

            return reply;
        };
    }
}
