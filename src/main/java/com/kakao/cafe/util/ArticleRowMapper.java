package com.kakao.cafe.util;

import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Article(
                rs.getLong("id"),
                rs.getLong("userId"),
                rs.getString("title"),
                rs.getString("body"),
                rs.getTimestamp("createdAt").toLocalDateTime(),
                rs.getInt("views")
        );
    }
}
