package com.kakao.cafe.qna;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
                rs.getInt("REPLY_COUNT"),
                rs.getString("IS_DELETED").equals("Y") ? Boolean.TRUE : Boolean.FALSE,
                Timestamp.valueOf(rs.getString("CREATED_DATE")).toLocalDateTime(),
                Timestamp.valueOf(rs.getString("MODIFIED_DATE")).toLocalDateTime());
    }
}
