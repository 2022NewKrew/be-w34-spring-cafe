package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("memberRepositoryJDBC")
public class MemberRepositoryJDBC implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public MemberRepositoryJDBC(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("Member").usingGeneratedKeyColumns("id");
    }

    @Override
    public int save(Member newMember) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", newMember.getUserId())
                .addValue("password", newMember.getPassword())
                .addValue("createdAt", newMember.getCreatedAt())
                .addValue("email", newMember.getEmail())
                .addValue("name", newMember.getName());

        Number id = simpleJdbcInsert.executeAndReturnKey(params);

        return id.intValue();
    }

    @Override
    public Optional<List<Member>> findAll() {
        String SQL = "select m.id as mId," +
                " m.userId as mUserId," +
                " m.password as mPassword," +
                " m.email as mEmail," +
                " m.name as mName," +
                " m.createdAt as mCreatedAt from Member m ";
        List<Member> members = jdbcTemplate.query(SQL, new MemberMapper());
        return Optional.of(members);
    }

    @Override
    public Member findByUserId(String userId) {
        String SQL = "select m.id as mId," +
                " m.userId as mUserId," +
                " m.password as mPassword," +
                " m.email as mEmail," +
                " m.name as mName," +
                " m.createdAt as mCreatedAt from Member m " +
                "WHERE m.userId = ?";
        try {
            return jdbcTemplate.queryForObject(SQL, new MemberMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Member findByEmail(String email) {
        String SQL = "select m.id as mId," +
                " m.userId as mUserId," +
                " m.password as mPassword," +
                " m.email as mEmail," +
                " m.name as mName," +
                " m.createdAt as mCreatedAt from Member m " +
                "WHERE m.email = ?";
        try {
            return jdbcTemplate.queryForObject(SQL, new MemberMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class MemberMapper implements RowMapper<Member> {
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
                    rs.getInt("mId"),
                    rs.getString("mUserId"),
                    rs.getString("mPassword"),
                    rs.getString("mEmail"),
                    rs.getString("mName"),
                    rs.getDate("mCreatedAt").toLocalDate()
            );
        }
    }
}
