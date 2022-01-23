package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ReplyRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcReplyRepository implements ReplyRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(ReplyRequestDTO reply) {
        String sql = "INSERT INTO REPLY (articleId, author, content, createdAt, deleted) VALUES (?, ?, ?, ?, 0)";
        jdbcTemplate.update(sql, reply.getArticleId(), reply.getAuthor(), reply.getContent(), LocalDateTime.now());
    }

    @Override
    public Optional<Reply> findById(Long id) {
        String sql = "SELECT r.id, r.articleId, r.author, u.name, r.content, r.createdAt FROM REPLY r join USERS u on r.author = u.userId WHERE r.id = ? AND NOT r.deleted";
        return jdbcTemplate.query(sql, this::replyRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public List<Reply> findByArticle(Long id) {
        String sql = "SELECT r.id, r.articleId, r.author, u.name, r.content, r.createdAt FROM REPLY r join USERS u on r.author = u.userId WHERE r.articleId = ? AND NOT r.deleted";
        return jdbcTemplate.query(sql, this::replyRowMapper, id);
    }

    @Override
    public void delete(Long id) {
        String sql = "UPDATE REPLY SET deleted = 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAll(Long id) {
        String sql = "UPDATE REPLY SET deleted = 1 WHERE articleId = ?";
        jdbcTemplate.update(sql, id);
    }

    private Reply replyRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return Reply.builder()
                .id(rs.getLong("id"))
                .articleId(rs.getLong("articleId"))
                .author(rs.getString("author"))
                .authorName(rs.getString("name"))
                .content(rs.getString("content"))
                .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
                .build();
    }
}
