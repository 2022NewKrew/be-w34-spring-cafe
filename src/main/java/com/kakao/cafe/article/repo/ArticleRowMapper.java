package com.kakao.cafe.article.repo;

import com.kakao.cafe.article.model.Article;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(rs.getLong("ID"));
        article.setTitle(rs.getString("TITLE"));
        article.setContent(rs.getString("CONTENT"));
        return article;
    }
}
