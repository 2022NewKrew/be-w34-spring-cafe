package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.reply.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class JdbcReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Reply reply) {
        jdbcTemplate.update(
                "INSERT INTO replies (ARTICLEID, WRITER, CONTENTS, CREATEDAT, DELETED) VALUES (?, ?, ?, ?, ?)",
                reply.getArticleId(), reply.getWriter(), reply.getContents(), Timestamp.valueOf(reply.getCreatedAt()), reply.getDeleted()
        );
    }

    @Override
    public List<Reply> selectAll(Long articleId) {
        return jdbcTemplate.query("SELECT * FROM replies", replyRowMapper()).stream()
                .filter(reply -> reply.getArticleId().equals(articleId))
                .filter(reply -> !reply.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reply> selectByReplyId(Long replyId) {
        List<Reply> result = jdbcTemplate.query("SELECT * FROM replies WHERE id = ?", replyRowMapper(), replyId);
        return result.stream().findAny();
    }

    @Override
    public void update(Reply reply) {
        jdbcTemplate.update("UPDATE replies SET contents=?, deleted=? WHERE id=?",
                reply.getContents(), reply.getDeleted(), reply.getId());
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> {
            return Reply.builder()
                    .id(rs.getLong("id"))
                    .articleId(rs.getLong("articleId"))
                    .writer(rs.getString("writer"))
                    .contents(rs.getString("contents"))
                    .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
                    .deleted(rs.getBoolean("deleted"))
                    .build();
        };
    }

}
