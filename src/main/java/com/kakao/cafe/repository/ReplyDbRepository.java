package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static com.kakao.cafe.query.reply.RepositoryQuery.*;

@Primary
@Repository
public class ReplyDbRepository implements ReplyRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReplyDbRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Reply> findById(String id) {
        return jdbcTemplate.query(selectByIdQuery, replyRowMapper(), id);
    }

    @Override
    public void addReply(Reply reply) {
        jdbcTemplate.update(insertQuery, reply.getWriter(), reply.getContents(), reply.getFK_article_id());
    }

    @Override
    public void deleteById(String id) {
        jdbcTemplate.update(deleteByIdQuery, id);
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setId(rs.getLong("id"));
            reply.setWriter(rs.getString("writer"));
            reply.setContents(rs.getString("contents"));
            reply.setFK_article_id(rs.getLong("fk_article_id"));
            return reply;
        };
    }
}
