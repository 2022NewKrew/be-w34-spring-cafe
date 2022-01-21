package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateReplyRepository implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Reply reply) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement("insert into REPLIES (USER_FK,ARTICLE_FK,WRITER,CONTENTS,WRITING_TIME)" +
                            "values (?,?,?,?,?)",
                    new String[]{"ID"});
            pstmt.setLong(1, reply.getUserFK());
            pstmt.setLong(2, reply.getArticleFK());
            pstmt.setString(3, reply.getWriter());
            pstmt.setString(4, reply.getContents());
            pstmt.setString(5, reply.getWritingTime());
            return pstmt;
        }, keyHolder);
    }

    @Override
    public Optional<Reply> findById(Long id) {
        return jdbcTemplate.query("select * from REPLIES where ID = ?",
                replyRowMapper(), id).stream().findAny();
    }

    @Override
    public List<Reply> findByArticleId(Long articlePK) {
        return jdbcTemplate.query("select * from REPLIES where ARTICLE_FK = ?",
                replyRowMapper(), articlePK);
    }

    @Override
    public List<Reply> findAll() {
        return jdbcTemplate.query("select * from REPLIES", replyRowMapper());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from REPLIES where ID = ?", id);
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> new Reply(
                rs.getLong("ID"),
                rs.getLong("USER_FK"),
                rs.getLong("ARTICLE_FK"),
                rs.getString("WRITER"),
                rs.getString("CONTENTS"),
                rs.getString("WRITING_TIME"));
    }
}
