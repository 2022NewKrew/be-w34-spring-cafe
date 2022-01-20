package com.kakao.cafe.qna.repository;

import com.kakao.cafe.qna.domain.Reply;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyH2Repository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReplyH2Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reply> findByQnaId(long qnaId) {
        return null;
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
    public void delete(long id, String userId) {

    }
}
