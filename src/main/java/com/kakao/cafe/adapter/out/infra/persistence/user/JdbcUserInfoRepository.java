package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.domain.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcUserInfoRepository implements UserInfoRepository {

    static final String COLUMN_ID = "id";
    static final String COLUMN_PASSWORD = "password";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_EMAIL = "email";
    private static final String USER_TABLE = "USER";
    private static final String QUERY_SELECT_ALL = "select * from " + USER_TABLE;

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper;

    public JdbcUserInfoRepository(DataSource dataSource, RowMapper<User> userMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName(USER_TABLE);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(COLUMN_ID, user.getUserId());
        parameters.put(COLUMN_PASSWORD, user.getPassword());
        parameters.put(COLUMN_NAME, user.getName());
        parameters.put(COLUMN_EMAIL, user.getEmail());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public void update(User user) {
        String sql = "update " + USER_TABLE + " set " + COLUMN_NAME + "=?, " + COLUMN_EMAIL + "=? where " + COLUMN_ID +
                     "=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getUserId());
    }

    @Override
    public List<User> getAllUserList() {
        return jdbcTemplate.query(QUERY_SELECT_ALL, userMapper);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String sql = QUERY_SELECT_ALL + " where " + COLUMN_ID + " = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userMapper, userId);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
