package com.kakao.cafe.question;

import com.kakao.cafe.question.mapper.QuestionRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

/**
 * H2 Database를 이용한 QuestionRepository 구현체입니다.
 *
 * @author jm.hong
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class QuestionRepositoryH2 implements QuestionRepository {

    private static final int FAIL = 0;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Question question) throws SQLException {

        String sql = "INSERT INTO question(writer, title, contents,member_id) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rs = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, question.getWriter());
            ps.setString(2, question.getTitle());
            ps.setString(3, question.getContents());
            ps.setLong(4, question.getMemberId());

            return ps;
        }, keyHolder);

        if (rs == FAIL) {
            throw new SQLException("QUESTION TABLE SAVE FAIL");
        }

        return (Long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public Question findOne(Long id) {

        String sql = "SELECT id, member_id, writer, title, contents, create_time, status FROM question WHERE id=? AND status != 'delete'";

        return jdbcTemplate.queryForObject(sql, new QuestionRowMapper(), id);
    }

    @Override
    public List<Question> findAll() {

        String sql = "SELECT id, member_id, writer, title, contents, create_time, status FROM question WHERE status != 'delete'";

        return jdbcTemplate.query(sql, new QuestionRowMapper());
    }

    @Override
    public List<Question> findPage(int currentPage, int pageSize) {

        String sql = "SELECT Q.id, Q.member_id, Q.writer, Q.title, Q.contents, Q.create_time, Q.status FROM (SELECT id, member_id, writer, title, contents, create_time, status FROM question WHERE status != 'delete' ORDER BY id DESC) Q LIMIT ?,?";

        return jdbcTemplate.query(sql, new QuestionRowMapper(), ((currentPage - 1) * pageSize), pageSize);
    }


    @Override
    public boolean deleteOne(Long id) {

        String sql = "UPDATE question SET status=? WHERE id = ?";

        int rs = jdbcTemplate.update(sql, QuestionStatus.DELETE.toString(), id);

        if (rs == FAIL) {
            log.error("USER TABLE UPDATE FAIL ");
            return false;
        }

        return true;
    }

    @Override
    public boolean updateOne(Question question) {

        String sql = "UPDATE question SET title=?, contents=? WHERE id=?";

        int rs = jdbcTemplate.update(sql,
                question.getTitle(),
                question.getContents(),
                question.getId()
        );

        if (rs == FAIL) {
            log.error("QUESTION TABLE DELETE FAIL ");
            return false;
        }

        return true;
    }

    @Override
    public int totalCount() {

        String sql = "SELECT count(id) as cnt FROM question WHERE status != 'delete'";

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
