package com.kakao.cafe.qna.repository;

import com.kakao.cafe.qna.domain.Reply;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyH2Repository implements ReplyRepository {

    private final RowMapper<Reply> replyRowMapper = getReplyRowMapper();
    private final JdbcTemplate jdbcTemplate;

    public ReplyH2Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reply> findByQnaId(long qnaId) {
        String sql = "SELECT * FROM replies WHERE qna_id = ?";
        return jdbcTemplate.query(sql, replyRowMapper, qnaId);
    }

    @Override
    public void create(Reply reply) {
        String sql = "INSERT INTO replies (qna_id, writer, contents, create_time) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            reply.getQnaId(),
            reply.getWriter(),
            reply.getContents(),
            reply.getCreateTime()
        );
    }

    @Override
    public void deleteByIdAndWriter(long id, String userId) {
        String sql = "DELETE FROM replies WHERE id = ? AND writer = ?";
        jdbcTemplate.update(sql, id, userId);
    }

    private RowMapper<Reply> getReplyRowMapper() {
        return ((resultSet, rowNum) -> new Reply(
            resultSet.getLong(1),
            resultSet.getLong(2),
            resultSet.getString(3),
            resultSet.getString(4)
        ));
    }
}
