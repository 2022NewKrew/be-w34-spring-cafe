package com.kakao.cafe.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Primary
@Repository
public class JdbcReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReplyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Reply reply) {
        jdbcTemplate.update(
                "INSERT INTO replys(user_seq,article_seq,writer,content) VALUES (?,?,?,?)",
                reply.getUserSeq(),
                reply.getArticleSeq(),
                reply.getWriter(),
                reply.getContent()
        );
    }

    @Override
    public void update(Reply reply) {
        jdbcTemplate.update(
                "UPDATE replys SET writer=?,content=? WHERE seq=? AND user_seq=? AND article_seq=?",
                reply.getWriter(),
                reply.getContent(),
                reply.getSeq(),
                reply.getUserSeq(),
                reply.getArticleSeq()
        );
    }

    @Override
    public void delete(Reply reply) {
        jdbcTemplate.update(
                "UPDATE replys SET deleted =? WHERE seq=? AND user_seq=? AND article_seq=?",
                true,
                reply.getSeq(),
                reply.getUserSeq(),
                reply.getArticleSeq()
        );
    }

    @Override
    public Optional<Reply> findBySeq(long seq) {
        List<Reply> replys = jdbcTemplate.query(
                "SELECT * FROM replys WHERE seq=?",
                mapper,
                seq
        );
        List<Reply> validReplys = replys.stream().filter(reply -> !reply.isDeleted()).collect(Collectors.toList());
        return ofNullable(validReplys.isEmpty() ? null : validReplys.get(0));
    }

    @Override
    public Optional<List<Reply>> findByArticleSeq(long articleSeq) {
        List<Reply> replys = jdbcTemplate.query(
                "SELECT * FROM replys WHERE article_seq=?",
                mapper,
                articleSeq
        );
        List<Reply> validReplys = replys.stream().filter(reply -> !reply.isDeleted()).collect(Collectors.toList());
        return ofNullable(validReplys.isEmpty() ? null : validReplys);
    }

    @Override
    public List<Reply> findAll() {
        List<Reply> replys = jdbcTemplate.query(
                "SELECT * FROM replys ORDER BY seq DESC",
                mapper
        );
        return replys.stream().filter(reply -> !reply.isDeleted()).collect(Collectors.toList());
    }

    static RowMapper<Reply> mapper = (rs, rowNum) ->
            Reply.builder()
                    .seq(rs.getLong("seq"))
                    .userSeq(rs.getLong("user_seq"))
                    .articleSeq(rs.getLong("article_seq"))
                    .writer(rs.getString("writer"))
                    .content(rs.getString("content"))
                    .time(rs.getTimestamp("time").toLocalDateTime())
                    .deleted(rs.getBoolean("deleted"))
                    .build();

}
