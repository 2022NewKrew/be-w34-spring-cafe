package com.kakao.cafe.article;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Articles> {
    @Override
    public Articles mapRow(ResultSet rs, int rowNum) throws SQLException {
        Articles article = new Articles();

        article.setId(rs.getLong("id"));
        article.setAuthor(rs.getString("author"));
        article.setCreatedAt(new java.sql.Timestamp(rs.getDate("created_at").getTime()).toLocalDateTime());
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));

        return article;
    }
}