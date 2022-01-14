package com.kakao.cafe.persistence.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Article(Integer.valueOf(rs.getString("article_id")),
                rs.getString("article_writer"),
                rs.getTimestamp("article_created_at").toLocalDateTime(),
                rs.getString("article_title"),
                rs.getString("article_contents"));
    }
}
