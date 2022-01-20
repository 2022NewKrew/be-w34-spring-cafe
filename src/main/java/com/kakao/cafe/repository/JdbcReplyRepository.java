package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcReplyRepository implements ReplyRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Reply> findAll()  {
        return jdbcTemplate.query("select * from reply", replyRowMapper());
    }

    @Override
    public List<Reply> findByArticleId(int articleId) {
        return jdbcTemplate.query("select * from reply where articleId = ?",
                replyRowMapper(),
                articleId);
    }

    @Override
    public Optional<Reply> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Reply reply) {
        jdbcTemplate.update("insert into reply(writer, content, date, articleId) values (?, ?, ?, ?)",
            reply.getWriter(),
            reply.getContent(),
            reply.getDate(),
            reply.getArticleId());
    }

    @Override
    public void update(Reply reply) {
        //
    }

    @Override
    public void delete(int id) {
        //
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> {
            Reply reply = new Reply(rs.getInt("id"),
                    rs.getString("writer"),
                    rs.getString("content"),
                    rs.getObject("date", LocalDateTime.class),
                    rs.getInt("articleId")
                    );
            return reply;
        };
    }
}
