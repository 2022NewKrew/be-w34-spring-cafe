package com.kakao.cafe.app.data;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        User owner = new User.Builder()
                .id(rs.getLong("users.id"))
                .name(rs.getString("users.name"))
                .email(rs.getString("users.email"))
                .userId(rs.getString("users.user_id"))
                .build();
        return new Article.Builder()
                .owner(owner)
                .id(rs.getLong("articles.id"))
                .author(rs.getString("articles.author"))
                .title(rs.getString("articles.title"))
                .content(rs.getString("articles.content"))
                .createdAt(new Date(rs.getTimestamp("articles.created_at").getTime()))
                .build();
    }
}
