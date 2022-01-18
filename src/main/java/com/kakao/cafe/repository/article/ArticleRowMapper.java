package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(rs.getInt("ID"));
        article.setTitle(new Title(rs.getString("TITLE")));
        article.setContent(new Content(rs.getString("CONTENT")));
        article.setCreatedAt(rs.getTimestamp("CREATEDAT").toLocalDateTime());
        article.setModifiedAt(rs.getTimestamp("MODIFIEDAT").toLocalDateTime());

        return article;
    }

}
