package com.kakao.cafe.repository;

import com.kakao.cafe.domain.reply.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReplyRepositoryJdbcImpl implements ReplyRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Reply> replyMapper = (rs, rowNum) -> {
        Reply reply = new Reply();
        reply.setId(rs.getLong("id"));
        reply.setUserId(rs.getLong("user_id"));
        reply.setArticleId(rs.getLong("article_id"));
        reply.setContent(rs.getString("content"));
        reply.setCreationTime(rs.getTimestamp("creation_time"));
        return reply;
    };

    @Autowired
    public ReplyRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Reply reply) {
        String INSERT_REPLY =
                "INSERT INTO reply (user_id, article_id, content) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(INSERT_REPLY, reply.getUserId(), reply.getArticleId(), reply.getContent());
    }

    @Override
    public List<Reply> findAll() {
        return null;
    }

    @Override
    public Optional<Reply> findById(Long id) {
        String SELECT_REPLY =
                "SELECT id, user_id, article_id, content, creation_time " +
                "FROM reply " +
                "WHERE id=? AND deleted=false";
        return jdbcTemplate.query(SELECT_REPLY, replyMapper, id)
                .stream().findFirst();
    }

    @Override
    public List<Reply> findAllByArticle(Long articleId) {
        String SELECT_REPLIES =
                "SELECT id, user_id, article_id, content, creation_time " +
                "FROM reply " +
                "WHERE article_id=? AND deleted=false ";
        return jdbcTemplate.query(SELECT_REPLIES, replyMapper, articleId);
    }

    @Override
    public void deleteById(Long id) {
        String DELETE_REPLY = "UPDATE reply SET deleted=true WHERE id=?";
        jdbcTemplate.update(DELETE_REPLY, id);
    }
}
