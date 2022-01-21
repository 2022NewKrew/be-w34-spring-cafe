package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ReplyMapper implements RowMapper<Reply> {

    private final UserMapper userMapper;

    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Reply.builder()
                .id(rs.getLong("reply_seq_id"))
                .articleId(rs.getLong("article_seq_id"))
                .writer(userMapper.mapRow(rs, rowNum))
                .comment(rs.getString("comment"))
                .build();
    }

}
