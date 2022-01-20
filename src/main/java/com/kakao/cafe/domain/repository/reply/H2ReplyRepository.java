package com.kakao.cafe.domain.repository.reply;

import com.kakao.cafe.domain.entity.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class H2ReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Reply reply) {
        if (reply.getId() == null) {
            insert(reply);
            return;
        }
        update(reply);
    }

    public void update(Reply reply) {
        String sql = "UPDATE `REPLY`" +
                "SET content = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql,
                reply.getContent(),
                reply.getId());
    }

    public void insert(Reply reply) {
        String sql = "INSERT INTO `REPLY`(author_id, article_id, content, created_at) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, reply.getAuthorId(), reply.getArticleId(), reply.getContent(), reply.getCreatedAt());
    }

    @Override
    public Optional<Reply> findById(Long id) {
        String sql = "SELECT r.id, r.author_id, u.name as author, r.article_id, r.content, r.created_at " +
                "FROM `REPLY` r " +
                "LEFT JOIN `USER` u ON r.author_id = u.id " +
                "WHERE r.id = ? and r.deleted = false";
        List<Reply> result = jdbcTemplate.query(sql, replyRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Reply> findAllByArticleId(Long articleId) {
        String sql = "SELECT r.id, r.author_id, u.name as author, r.article_id, r.content, r.created_at " +
                "FROM `REPLY` r LEFT JOIN `USER` u ON r.author_id = u.id " +
                "WHERE r.article_id = ? and r.deleted = false";
        return jdbcTemplate.query(sql, replyRowMapper(), articleId);
    }

    @Override
    public void delete(Long id) {
        String sql = "UPDATE `REPLY` SET deleted = true WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setId(rs.getLong("id"));
            reply.setAuthorId(rs.getLong("author_id"));
            reply.setAuthor(rs.getString("author"));
            reply.setArticleId(rs.getLong("article_id"));
            reply.setContent(rs.getString("content"));
            reply.setCreatedAt(rs.getDate("created_at"));
            return reply;
        };
    }
}
