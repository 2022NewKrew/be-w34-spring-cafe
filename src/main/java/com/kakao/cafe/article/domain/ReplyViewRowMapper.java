package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.ReplyViewDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyViewRowMapper implements RowMapper {

    @Override
    public ReplyViewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReplyViewDTO replyViewDTO = new ReplyViewDTO(
                                        rs.getLong("REPLY.sequence"),
                                        rs.getString("REPLY.userId"),
                                        rs.getString("USERS.name"),
                                        rs.getString("REPLY.contents"),
                                        rs.getTimestamp("REPLY.createdAt")
        );
        return replyViewDTO;
    }
}
