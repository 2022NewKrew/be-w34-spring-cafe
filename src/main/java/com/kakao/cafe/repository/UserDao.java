package com.kakao.cafe.repository;

import com.kakao.cafe.repository.constants.UserDBConstants;
import com.kakao.cafe.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *  사용자 DB 관련 CRUD 처리
 */
public class UserDao implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(User user) throws SQLException {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        Map<String, Object> param = new HashMap<>();

        simpleJdbcInsert
                .withTableName(UserDBConstants.TABLE_NAME)
                .usingColumns(UserDBConstants.COLUMN_USERID, UserDBConstants.COLUMN_EMAIL, UserDBConstants.COLUMN_NAME, UserDBConstants.COLUMN_PASSWORD)
                .usingGeneratedKeyColumns(UserDBConstants.COLUMN_ID);

        param.put(UserDBConstants.COLUMN_USERID, user.getUserId());
        param.put(UserDBConstants.COLUMN_EMAIL, user.getEmail());
        param.put(UserDBConstants.COLUMN_NAME, user.getName());
        param.put(UserDBConstants.COLUMN_PASSWORD, user.getPassword());

        int key = simpleJdbcInsert.executeAndReturnKey(param).intValue();

        if (key < 1)
            throw new SQLException("MEMBER insertion fail.");

        return key;
    }

    public User findByUserId(String userId) throws NoSuchElementException {
        User user;
        String sql = String.format("select * from %s where %s = ?", UserDBConstants.TABLE_NAME, UserDBConstants.COLUMN_USERID);

        try {
            user = jdbcTemplate.queryForObject(
                    sql,
                    new UserMapper(),
                    userId
            );
        } catch (DataAccessException e) {
            throw new NoSuchElementException(String.format("id (%s) 를 갖는 유저가 존재하지 않음", userId));
        }

        return user;
    }

    public User findByName(String name) throws NoSuchElementException {
        User user;
        String sql = String.format("select * from %s where %s = ?", UserDBConstants.TABLE_NAME, UserDBConstants.COLUMN_NAME);

        try {
            user = jdbcTemplate.queryForObject(
                    sql,
                    new UserMapper(),
                    name
            );
        } catch (DataAccessException e) {
            throw new NoSuchElementException(String.format("name (%s) 을 갖는 유저가 존재하지 않음.", name));
        }

        return user;
    }

    public List<User> findAll() {
        String sql = String.format("select * from %s", UserDBConstants.TABLE_NAME);
        return jdbcTemplate.query(
                sql,
                new UserMapper()
        );
    }

    public void update(User user) {
        String sql = String.format("update %s set %s = ?, %s = ?  where %s = ?", UserDBConstants.TABLE_NAME, UserDBConstants.COLUMN_EMAIL, UserDBConstants.COLUMN_NAME, UserDBConstants.COLUMN_USERID);

        jdbcTemplate.update(sql,
                user.getEmail(),
                user.getName(),
                user.getUserId()
        );
    }

    public int count() {
        String sql = String.format("select count(*) from %s", UserDBConstants.TABLE_NAME);
        try {
            return jdbcTemplate.queryForObject(sql, int.class);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int count) throws SQLException {

            return new User(
                    rs.getInt(UserDBConstants.COLUMN_ID),
                    rs.getString(UserDBConstants.COLUMN_USERID),
                    rs.getString(UserDBConstants.COLUMN_EMAIL),
                    rs.getString(UserDBConstants.COLUMN_NAME),
                    rs.getString(UserDBConstants.COLUMN_PASSWORD)
            );
        };
    }
}
