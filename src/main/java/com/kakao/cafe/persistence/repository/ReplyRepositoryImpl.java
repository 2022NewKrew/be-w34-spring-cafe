package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.Reply;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Reply reply) {
        String sql = "INSERT INTO REPLY (article_id, uid, user_name, body, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, reply.getArticleId(), reply.getUid(), reply.getUserName(),
            reply.getBody(), reply.getCreatedAt());
    }

    @Override
    public void update(Long id, String body) {
        String sql = "UPDATE REPLY SET body = ? WHERE id = ?";
        jdbcTemplate.update(sql, body, id);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM REPLY WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Reply> findById(Long id) {
        String sql = "SELECT * FROM REPLY WHERE id = ?";
        return jdbcTemplate.query(sql, this::replyRowMapper, id).stream()
            .findFirst();
    }

    @Override
    public List<Reply> findByArticleId(Long articleId) {
        String sql = "SELECT * FROM REPLY WHERE article_id = ?";
        return jdbcTemplate.query(sql, this::replyRowMapper, articleId);
    }

    private Reply replyRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return Reply.builder()
            .id(rs.getLong("id"))
            .articleId(rs.getLong("article_id"))
            .uid(rs.getString("uid"))
            .userName(rs.getString("user_name"))
            .body(rs.getString("body"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
            .build();
    }
}
