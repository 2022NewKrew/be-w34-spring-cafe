package com.kakao.cafe.Repository;

import com.kakao.cafe.Dto.Reply.ReplyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDao {
    private final JdbcTemplate jdbcTemplate;
    private final ReplyMapper replyMapper = new ReplyMapper();

    public List<ReplyResponseDto> findByPostId(int postId) {
        String sql = "SELECT CONTENT, WRITER FROM REPLIES WHERE POST_ID = ?";

        return jdbcTemplate.query(sql, replyMapper, postId);
    }

    private static class ReplyMapper implements RowMapper<ReplyResponseDto> {
        @Override
        public ReplyResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReplyResponseDto(
                    rs.getString("CONTENT"),
                    rs.getString("WRITER")
            );
        }
    }
}
