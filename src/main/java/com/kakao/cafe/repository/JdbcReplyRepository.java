package com.kakao.cafe.repository;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.repository.mapper.ReplyRowMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ReplyRowMapper rowMapper;

    public JdbcReplyRepository(JdbcTemplate jdbcTemplate, ReplyRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(Reply reply) {
        jdbcTemplate.update("INSERT INTO replies (comment, users_id, articles_id) VALUES (?, ?, ?)",
                reply.getComment().getValue(),
                reply.getWriter().getId().toString(),
                reply.getArticleId().toString());
    }

    @Override
    public List<Reply> findAllByArticleId(UUID articleId) {
        return jdbcTemplate.query("SELECT * FROM "
                + "replies INNER JOIN users USING(users_id) "
                + "WHERE articles_id = ? AND deleted = FALSE", rowMapper, articleId.toString());
    }

    @Override
    public Optional<Reply> findReplyById(UUID replyId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM "
                    + "replies INNER JOIN users USING(users_id) "
                    + "WHERE replies_id = ? AND deleted = FALSE", rowMapper, replyId.toString()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Reply reply) {
        jdbcTemplate.update("UPDATE replies SET deleted = TRUE "
                        + "WHERE replies_id = ?",
                reply.getReplyId().toString());
    }

    @Override
    public void deleteByArticleId(UUID articleId) {
        jdbcTemplate.update("UPDATE replies SET deleted = TRUE "
                        + "WHERE articles_id = ?",
                articleId.toString());
    }
}
