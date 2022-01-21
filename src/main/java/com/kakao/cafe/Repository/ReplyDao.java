package com.kakao.cafe.Repository;

import com.kakao.cafe.Dto.Reply.ReplyRequestDto;
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

    public void insert(ReplyRequestDto reply) {
        String sql = "INSERT INTO REPLY(COMMENT, WRITER, POST_ID) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql,
                reply.getComment(), reply.getWriter(), reply.getPostId());
    }

    public ReplyResponseDto findById(int id) {
        String sql = "SELECT ID, COMMENT, WRITER FROM REPLY WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, replyMapper, id);
    }

    public List<ReplyResponseDto> findByPostId(int postId) {
        String sql = "SELECT ID, COMMENT, WRITER FROM REPLY WHERE POST_ID = ?";

        return jdbcTemplate.query(sql, replyMapper, postId);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM REPLY WHERE ID = ?";

        jdbcTemplate.update(sql, id);
    }

    private static class ReplyMapper implements RowMapper<ReplyResponseDto> {
        @Override
        public ReplyResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReplyResponseDto(
                    rs.getInt("ID"),
                    rs.getString("COMMENT"),
                    rs.getString("WRITER")
            );
        }
    }
}
