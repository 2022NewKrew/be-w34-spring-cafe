package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("ID"));
        article.setTitle(rs.getString("TITLE"));
        article.setContent(rs.getString("CONTENT"));
        article.setCreatedAt(rs.getTimestamp("CREATEDAT"));
        article.setModifiedAt(rs.getTimestamp("MODIFIEDAT"));

        return article;
    }

}
