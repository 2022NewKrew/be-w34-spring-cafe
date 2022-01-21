package com.kakao.cafe.util;

import com.kakao.cafe.entity.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Article(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("content")
//                rs.getTimestamp("created_at"),
//                rs.getInt("user_id")
        );
    }

}
