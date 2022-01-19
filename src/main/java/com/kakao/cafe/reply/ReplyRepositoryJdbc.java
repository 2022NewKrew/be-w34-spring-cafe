package com.kakao.cafe.reply;

import com.kakao.cafe.question.QuestionStatus;
import com.kakao.cafe.question.mapper.QuestionRowMapper;
import com.kakao.cafe.reply.mapper.ReplyRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ReplyRepositoryJdbc implements ReplyRepository {

    private static final int FAIL = 0;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Reply reply) throws SQLException {

        String sql = "INSERT INTO reply(member_id,comment,writer,question_id) values (?, ?, ?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rs = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, reply.getMemberId());
            ps.setString(2, reply.getComment());
            ps.setString(3, reply.getWriter());
            ps.setLong(4, reply.getQuestionId());

            return ps;
        }, keyHolder);

        if (rs == FAIL) {
            throw new SQLException("Reply TABLE SAVE FAIL");
        }

        return (Long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public boolean deleteOne(Long id) {

        String sql = "UPDATE reply SET status=? WHERE id = ?";

        int rs = jdbcTemplate.update(sql, QuestionStatus.DELETE.toString(), id);

        if (rs == FAIL) {
            log.error("USER TABLE DELETE FAIL ");
            return false;
        }

        return true;
    }

    @Override
    public List<Reply> findAllAsQuestionId(Long questionId) {

        String sql = "SELECT id, member_id, question_id, writer, comment, create_time, status FROM reply where question_id = ? and status != 'delete'";

        return jdbcTemplate.query(sql, new ReplyRowMapper(), questionId);
    }

    @Override
    public Reply findOne(Long id) {

        String sql = "SELECT id, member_id, question_id, writer, comment, create_time, status FROM reply WHERE id=?";

        return jdbcTemplate.queryForObject(sql, new ReplyRowMapper(), id);
    }

    @Override
    public boolean deleteAsQuestionId(Long id) {

        String sql = "UPDATE reply SET status=? WHERE question_id = ?";

        int rs = jdbcTemplate.update(sql, QuestionStatus.DELETE.toString(), id);

        if (rs == FAIL) {
            log.error("USER TABLE DELETE FAIL ");
            return false;
        }

        return true;

    }
}
