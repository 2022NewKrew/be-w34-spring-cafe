package com.kakao.cafe.article.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyRowMapper implements RowMapper {

    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reply reply = new Reply(
                                        rs.getLong("sequence"),
                                        rs.getString("userId"),
                                        rs.getLong("articleSeq"),
                                        rs.getString("contents"),
                                        rs.getTimestamp("createdAt")
        );
        return reply;
    }
}
