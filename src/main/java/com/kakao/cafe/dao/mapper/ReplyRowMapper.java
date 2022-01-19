package com.kakao.cafe.dao.mapper;

import com.kakao.cafe.vo.ReplyVo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReplyRowMapper implements RowMapper<ReplyVo> {
    @Override
    public ReplyVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        int replyId = rs.getInt("replyId");
        String writer = rs.getString("writer");
        String comment = rs.getString("comment");
        String date = rs.getString("date");
        int articleId = rs.getInt("articleId");
        String userId = rs.getString("userId");
        return new ReplyVo(replyId, writer, comment, date, articleId, userId);
    }
}
