package com.kakao.cafe.dao.mapper;

import com.kakao.cafe.vo.ArticleVo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleRowMapper implements RowMapper<ArticleVo> {
    @Override
    public ArticleVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        int articleId = rs.getInt("articleId");
        String writer = rs.getString("writer");
        String title = rs.getString("title");
        String contents = rs.getString("contents");
        String date = rs.getString("date");
        String userId = rs.getString("userId");
        return new ArticleVo(articleId, writer, title, contents, date, userId);
    }
}
