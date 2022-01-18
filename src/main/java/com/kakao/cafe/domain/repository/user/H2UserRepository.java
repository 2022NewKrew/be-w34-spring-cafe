package com.kakao.cafe.domain.repository.user;

import com.kakao.cafe.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class H2UserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        String sql = "MERGE INTO `USER`(JOINED_AT, USERID, PASSWORD, `NAME`, EMAIL) " +
                "KEY(USERID) " +
                "VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,
                user.getJoinedAt(), user.getUserId(), user.getHashedPw(), user.getName(), user.getEmail());

        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM \"USER\" WHERE ID = ?";
        List<User> result = jdbcTemplate.query(sql, userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String sql = "SELECT * FROM \"USER\" WHERE USERID = ?";
        List<User> result = jdbcTemplate.query(sql, userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM \"USER\"", userRowMapper());
    }


    @Override
    public long countRecords() {
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("SELECT COUNT(*) FROM \"USER\"");
            }
        }, new ResultSetExtractor<Long>() {
            @Override
            public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getLong(1);
            }
        });
    }


    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setJoinedAt(rs.getDate("joined_at"));
            user.setUserId(rs.getString("userid"));
            user.setHashedPw(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        };
    }
}
