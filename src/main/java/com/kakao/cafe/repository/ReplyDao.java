package com.kakao.cafe.repository;

import com.kakao.cafe.constants.ReplyDBConstants;
import com.kakao.cafe.constants.UserDBConstants;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplyDao implements ReplyRepository{
    private final JdbcTemplate jdbcTemplate;

    public ReplyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Reply reply) throws SQLException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Map<String, Object> param = new HashMap<>();

        simpleJdbcInsert
                .withTableName(ReplyDBConstants.TABLE_NAME)
                .usingColumns(ReplyDBConstants.COLUMN_ARTICLE_ID, ReplyDBConstants.COLUMN_WRITER, ReplyDBConstants.COLUMN_CONTENTS)
                .usingGeneratedKeyColumns(ReplyDBConstants.COLUMN_ID);

        param.put(ReplyDBConstants.COLUMN_ARTICLE_ID, reply.getAid());
        param.put(ReplyDBConstants.COLUMN_WRITER, reply.getWriter());
        param.put(ReplyDBConstants.COLUMN_CONTENTS, reply.getContents());

        int key = simpleJdbcInsert.executeAndReturnKey(param).intValue();

        if (key < 1)
            throw new SQLException("Reply insertion fail.");

        return key;
    }

    public List<Reply> findByAid(int aid) {
        String sql = String.format("select * from %s where %s = ?", ReplyDBConstants.TABLE_NAME, ReplyDBConstants.COLUMN_ARTICLE_ID);

        return jdbcTemplate.query(
                sql,
                new ReplyMapper(),
                aid
        );
    }

    public static class ReplyMapper implements RowMapper<Reply> {
        @Override
        public Reply mapRow(ResultSet rs, int count) throws SQLException {

            return new Reply(
                    rs.getInt(ReplyDBConstants.COLUMN_ID),
                    rs.getInt(ReplyDBConstants.COLUMN_ARTICLE_ID),
                    rs.getString(ReplyDBConstants.COLUMN_WRITER),
                    rs.getString(ReplyDBConstants.COLUMN_CONTENTS)
            );
        };
    }
}
