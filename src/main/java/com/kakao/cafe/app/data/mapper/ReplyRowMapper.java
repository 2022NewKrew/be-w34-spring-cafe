package com.kakao.cafe.app.data.mapper;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.domain.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReplyRowMapper implements RowMapper<Reply> {

    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        User author = new User.Builder()
                .id(rs.getLong("users.id"))
                .name(rs.getString("users.name"))
                .email(rs.getString("users.email"))
                .userId(rs.getString("users.user_id"))
                .build();
        Article target = new Article.Builder()
                .id(rs.getLong("articles.id"))
                .build();
        return new Reply.Builder()
                .author(author)
                .id(rs.getLong("replies.id"))
                .target(target)
                .content(rs.getString("replies.content"))
                .createdAt(new Date(rs.getTimestamp("replies.created_at").getTime()))
                .build();
    }
}
