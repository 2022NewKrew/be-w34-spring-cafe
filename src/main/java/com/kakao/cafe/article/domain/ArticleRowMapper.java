package com.kakao.cafe.article.domain;


import com.kakao.cafe.article.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article(rs.getString("name"),
                                        rs.getString("title"),
                                        rs.getString("contents"),
                                        rs.getTimestamp("createdAt"),
                                        rs.getLong("sequence"));


        return article;
    }
}
