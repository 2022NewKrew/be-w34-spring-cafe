package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.repository.reply.mapper.ReplyRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
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
@Slf4j
public class JdbcReplyRepository implements ReplyRepository{

    private final JdbcTemplate jdbcTemplate;
    private final ReplyRowMapper replyRowMapper;

    private static final String SELECT_BY_ARTICLE_ID_QUERY
            = "SELECT r.id as id, article_id, writer_id, u.user_name as writer_name, comment, created_time, updated_time FROM replies as r INNER JOIN users as u ON r.writer_id = u.user_id WHERE article_id = ? AND is_deleted=false";
    private static final String SELECT_BY_ID_QUERY
            = "SELECT r.id as id, article_id, writer_id, u.user_name as writer_name, comment, created_time, updated_time FROM replies as r INNER JOIN users as u ON r.writer_id = u.user_id WHERE r.id = ? AND is_deleted=false";
    private static final String INSERT_REPLY_QUERY = "INSERT INTO replies (article_id, writer_id, comment, created_time) VALUES (?, ?, ?, ?)";
    private static final String DELETE_REPLY_QUERY = "UPDATE replies SET is_deleted=true WHERE id = ?";

    @Override
    public List<Reply> findByArticleId(Long articleId) {
        return jdbcTemplate.query(SELECT_BY_ARTICLE_ID_QUERY, replyRowMapper, articleId);
    }

    @Override
    public Optional<Reply> findById(Long replyId) {
        List<Reply> replies = jdbcTemplate.query(SELECT_BY_ID_QUERY, replyRowMapper, replyId);
        return replies.stream().findFirst();
    }

    @Override
    public Long insert(Reply reply) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(INSERT_REPLY_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setLong(1, reply.getArticleId());
                ps.setString(2, reply.getWriter().getUserId());
                ps.setString(3, reply.getComment());
                ps.setTimestamp(4, Timestamp.valueOf(reply.getCreatedTime()));
                return ps;
            }, keyHolder);
            reply.updateId(keyHolder.getKey().longValue());
        } catch (DataAccessException ex) {
            log.warn("{} - Reply : {}", ex.getMessage(), reply);
            throw ex;
        }
        return reply.getId();
    }

    @Override
    public void delete(Long replyId) {
        try {
            jdbcTemplate.update(DELETE_REPLY_QUERY, replyId);
        } catch (DataAccessException ex) {
            log.warn("{} - ReplyId: {}", ex.getMessage(), replyId);
            throw ex;
        }
    }
}
