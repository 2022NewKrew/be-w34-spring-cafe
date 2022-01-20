package com.kakao.cafe.qna.article;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by melodist
 * Date: 2022-01-12 012
 * Time: 오후 3:19
 */
public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Article(
                rs.getInt("ID"),
                rs.getString("WRITER"),
                rs.getString("TITLE"),
                rs.getString("CONTENTS"),
                rs.getInt("COMMENTS_COUNT"),
                rs.getString("IS_DELETED").equals("Y") ? Boolean.TRUE : Boolean.FALSE,
                rs.getTimestamp("CREATED_DATE").toLocalDateTime(),
                rs.getTimestamp("MODIFIED_DATE").toLocalDateTime());
    }
}
