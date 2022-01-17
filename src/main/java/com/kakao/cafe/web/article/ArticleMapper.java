package com.kakao.cafe.web.article;

import com.kakao.cafe.web.article.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();

        article.setId(rs.getInt("id"));
        article.setContent(rs.getString("content"));
        article.setTitle(rs.getString("title"));
        article.setWriter(rs.getString("writer"));
        article.setDate(rs.getDate("create_date"));

        return article;
    }
}
