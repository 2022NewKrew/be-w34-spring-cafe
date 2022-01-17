package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *  사용자 DB 관련 CRUD 처리
 */
public class UserDao implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String COLUMN_ID = "id";
    private final String COLUMN_USERID = "userId";
    private final String COLUMN_EMAIL = "email";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_PASSWORD = "password";

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) throws SQLException {
        String sql = "insert into MEMBER values(?,?,?,?)"; // sysdate

        int result = jdbcTemplate.update(sql,
                user.getUserId(),
                user.getEmail(),
                user.getName(),
                user.getPassword()
        );

        if (result != 1)
            throw new SQLException("User insertion fail.");
    }

    public User findByUserId(String userId) throws NoSuchElementException {
        User user;

        try {
            user = jdbcTemplate.queryForObject(
                    "select * from MEMBER where userId = ?",
                    new UserMapper(),
                    userId
            );
        } catch (DataAccessException e) {
            throw new NoSuchElementException("해당 Id를 갖는 유저가 존재하지 않음");
        }

        return user;
    }

    public User findByName(String name) throws NoSuchElementException {
        User user;

        try {
            user = jdbcTemplate.queryForObject(
                    "select * from MEMBER where name = ?",
                    new UserMapper(),
                    name
            );
        } catch (DataAccessException e) {
            throw new NoSuchElementException("해당 name을 갖는 유저가 존재하지 않음");
        }

        return user;
    }

    public List<User> findAll() {

        System.out.println(count());
        return jdbcTemplate.query(
                "select * from MEMBER",
                new UserMapper()
        );
    }

    public void update(User user) {
        String sql = "update MEMBER set email = ?, name = ?  where userId = ?";

        jdbcTemplate.update(sql,
                user.getEmail(),
                user.getName(),
                user.getUserId()
        );
    }

    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from MEMBER", int.class);
    }

    public class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int count) throws SQLException {

            return new User(
                    rs.getInt(COLUMN_ID),
                    rs.getString(COLUMN_USERID),
                    rs.getString(COLUMN_EMAIL),
                    rs.getString(COLUMN_NAME),
                    rs.getString(COLUMN_PASSWORD)
            );
        };
    }
}
