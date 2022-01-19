package com.kakao.cafe.dao;

import com.kakao.cafe.vo.Reply;
import com.kakao.cafe.vo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReplyDao {

    private final JdbcTemplate jdbcTemplate;

    public ReplyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> new Reply(
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getInt("articleId"),
                rs.getInt("id")
        );
    }

    public List<Reply> getReplies(int articleId) {
        return jdbcTemplate.query("SELECT * FROM replys WHERE articleId = ? AND deleted = ?",
                replyRowMapper(), articleId, false);
    }

    public void addReply(Reply reply) {
        jdbcTemplate.update("INSERT INTO replys(writer, contents, articleId, deleted) VALUES(?,?,?,?)",
                reply.getWriter(), reply.getContents(), reply.getArticleId(), false);
    }

    public Reply getReply(int id) {
        return jdbcTemplate.query("SELECT * FROM replys WHERE id = ?", replyRowMapper(), id)
                .stream().findFirst().orElse(null);
    }

    public void updateReply(Reply reply) {
        jdbcTemplate.update("UPDATE replys SET contents=? WHERE id=?",
                reply.getContents(), reply.getId());
    }

    public void deleteReply(int id) {
        jdbcTemplate.update("UPDATE replys SET deleted=? WHERE id=?", true, id);
    }

}
