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
        jdbcTemplate.update("INSERT INTO replies (comment, users_id, articles_id) VALUES (?, UUID_TO_BIN(?), UUID_TO_BIN(?))",
                reply.getComment().getValue(),
                reply.getWriter().getId().toString(),
                reply.getArticleId().toString());
    }

    @Override
    public List<Reply> findAllByArticleId(UUID articleId) {
        return jdbcTemplate.query("SELECT " +
                "BIN_TO_UUID(replies_id) as replies_id, " +
                "comment, " +
                "created_at, " +
                "deleted, " +
                "BIN_TO_UUID(articles_id) as articles_id, " +
                "BIN_TO_UUID(users_id) as users_id, " +
                "username, " +
                "password, " +
                "name, " +
                "email " +
                "FROM replies INNER JOIN users USING(users_id) " +
                "WHERE articles_id = UUID_TO_BIN(?) AND deleted = FALSE", rowMapper, articleId.toString());
    }

    @Override
    public Optional<Reply> findReplyById(UUID replyId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT " +
                    "BIN_TO_UUID(replies_id) as replies_id, " +
                    "comment, " +
                    "created_at, " +
                    "deleted, " +
                    "BIN_TO_UUID(articles_id) as articles_id, " +
                    "BIN_TO_UUID(users_id) as users_id, " +
                    "username, " +
                    "password, " +
                    "name, " +
                    "email " +
                    "FROM replies INNER JOIN users USING(users_id) " +
                    "WHERE replies_id = UUID_TO_BIN(?) AND deleted = FALSE", rowMapper, replyId.toString()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Reply reply) {
        jdbcTemplate.update("UPDATE replies SET deleted = TRUE "
                        + "WHERE replies_id = UUID_TO_BIN(?)",
                reply.getReplyId().toString());
    }

    @Override
    public void deleteByArticleId(UUID articleId) {
        jdbcTemplate.update("UPDATE replies SET deleted = TRUE "
                        + "WHERE articles_id = UUID_TO_BIN(?)",
                articleId.toString());
    }
}
