package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Entity.Article;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet resultSet, int count) throws SQLException {
        int id = resultSet.getInt("id");
        String writer = resultSet.getString("writer");
        String title = resultSet.getString("title");
        String contents = resultSet.getString("contents");
        return new Article(id, writer, title, contents);
    }
}
