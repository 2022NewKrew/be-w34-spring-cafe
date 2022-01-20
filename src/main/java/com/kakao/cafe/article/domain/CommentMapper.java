package com.kakao.cafe.article.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Comment.builder()
            .id(rs.getLong("comment_id"))
            .body(rs.getString("body"))
            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
            .authorId(rs.getLong("author_id"))
            .articleId(rs.getLong("article_id"))
            .build();
    }
}
