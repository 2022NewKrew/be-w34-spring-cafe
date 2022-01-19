package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Member;
import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public ReplyRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("Reply").usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(Reply reply) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("content", reply.getContent())
                .addValue("createdAt", reply.getCreatedAt())
                .addValue("postId", reply.getPost().getId())
                .addValue("isRemoved", reply.isRemoved())
                .addValue("writerId", reply.getWriter().getId());

        simpleJdbcInsert.executeAndReturnKey(params);
    }

    @Override
    public void remove(Reply answer) {
        this.jdbcTemplate.update("update Reply set isRemoved=true where id=?", answer.getId());
    }

    @Override
    public Optional<Reply> findById(int answerId) {
        String ReplySQL = "select r.id as rId, " +
                "r.content as rContent, " +
                "r.createdAt as rCreatedAt, " +
                "m.id as mId, " +
                "m.userId as mUserId, " +
                "m.password as mPassword, " +
                "m.email as mEmail, " +
                "m.name as mName, " +
                "m.createdAt as mCreatedAt " +
                "FROM Reply r " +
                "INNER JOIN Member m on r.writerId = m.id " +
                "WHERE r.id = ? and r.isRemoved = false";

        try {
            Reply reply = jdbcTemplate.queryForObject(ReplySQL, new ReplyMapper(), answerId);
            return Optional.ofNullable(reply);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class ReplyMapper implements RowMapper<Reply> {
        public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
                    rs.getInt("mId"),
                    rs.getString("mUserId"),
                    rs.getString("mPassword"),
                    rs.getString("mEmail"),
                    rs.getString("mName"),
                    rs.getDate("mCreatedAt").toLocalDate()
            );

            return new Reply(
                    rs.getInt("rId"),
                    rs.getString("rContent"),
                    Instant.ofEpochMilli(rs.getDate("rCreatedAt").getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime(),
                    member
            );
        }
    }
}
