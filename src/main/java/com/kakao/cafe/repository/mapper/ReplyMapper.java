package com.kakao.cafe.repository.mapper;

import com.kakao.cafe.domain.article.reply.Reply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReplyMapper implements RowMapper<Reply> {
    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long articleId = rs.getLong("article_id");
        String author = rs.getString("author");
        String comment = rs.getString("comment");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        Reply reply =  new Reply(articleId, author, comment, createdAt);
        reply.setId(id);
        return reply;
    }
}
