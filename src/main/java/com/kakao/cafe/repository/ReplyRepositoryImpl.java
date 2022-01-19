package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.reply.Reply;
import com.kakao.cafe.domain.article.reply.ReplyRepository;
import com.kakao.cafe.repository.mapper.ReplyMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Reply> rowMapper;

    public ReplyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new ReplyMapper();
    }

    @Override
    public Long save(Reply reply) {
        if(reply.getId() == null) {
            return insertReply(reply);
        }
        return updateReply(reply);
    }

    @Override
    public List<Reply> findRepliesByArticle(Article article) {
        List<Reply> replies = jdbcTemplate.query("SELECT * FROM REPLY WHERE article_id = ? AND is_deleted IS FALSE", rowMapper, article.getId());
        return replies;
    }

    @Override
    public Optional<Reply> findReplyById(Long replyId) {
        Reply reply = null;
        try {
            reply = jdbcTemplate.queryForObject("SELECT * FROM REPLY WHERE id = ? AND is_deleted IS FALSE", rowMapper, replyId);
        } catch (EmptyResultDataAccessException ignored) {}
        return Optional.ofNullable(reply);
    }

    @Override
    public Long delete(Reply reply) {
        jdbcTemplate.update("DELETE FROM REPLY WHERE id = ?", reply.getId());
        return reply.getId();
    }

    private Long insertReply(Reply reply) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO REPLY(article_id, author, comment, is_deleted, created_at) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, reply.getArticleId());
            statement.setString(2, reply.getAuthor());
            statement.setString(3, reply.getComment());
            statement.setBoolean(4, reply.getDeleted());
            statement.setTimestamp(5, Timestamp.valueOf(reply.getCreatedAt()));
            return statement;
        }, generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

    private Long updateReply(Reply reply) {
        jdbcTemplate.update("UPDATE REPLY SET comment = ?, is_deleted = ? WHERE id = ?",
                reply.getComment(),
                reply.getDeleted(),
                reply.getId());
        return reply.getId();
    }
}
