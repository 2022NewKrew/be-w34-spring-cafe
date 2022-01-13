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
        int id = rs.getInt("id");
        String writer = rs.getString("writer");
        String title = rs.getString("title");
        String contents = rs.getString("contents");
        String date = rs.getString("date");
        return new ArticleVo(id,writer,title,contents,date);
    }
}
