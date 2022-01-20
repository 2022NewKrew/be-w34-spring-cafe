package com.kakao.cafe.qna.comment;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 6:25
 */
public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Comment(rs.getInt("ID"),
                rs.getInt("WRITER_ID"),
                rs.getString("WRITER"),
                rs.getString("CONTENTS"),
                rs.getInt("ARTICLE_ID"),
                rs.getString("IS_DELETED").equals("Y") ? Boolean.TRUE : Boolean.FALSE,
                rs.getTimestamp("CREATED_DATE").toLocalDateTime(),
                rs.getTimestamp("MODIFIED_DATE").toLocalDateTime()
        );
    }
}
