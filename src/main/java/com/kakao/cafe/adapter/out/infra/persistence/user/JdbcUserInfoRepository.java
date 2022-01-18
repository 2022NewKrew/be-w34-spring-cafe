package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private static final String USER_TABLE = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String SELECT_ALL = "select * from " + USER_TABLE;

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserInfoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        String sql = "update user set " + COLUMN_NAME + "=?, " + COLUMN_EMAIL + "=? where " + COLUMN_ID + "=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getUserId());
    }

    @Override
    public List<User> getAllUserList() {
        return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> new UserMapper().mapRow(rs, rowNum));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String sql = SELECT_ALL + " where " + COLUMN_ID + " = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new UserMapper().mapRow(rs, rowNum), userId);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                return new User.Builder().userId(rs.getString(COLUMN_ID))
                                         .password(rs.getString(COLUMN_PASSWORD))
                                         .name(rs.getString(COLUMN_NAME))
                                         .email(rs.getString(COLUMN_EMAIL))
                                         .build();
            } catch (IllegalUserIdException e) {
                throw new SQLException("DB에 저장된 userID가 잘못되었습니다.");
            } catch (IllegalPasswordException e) {
                throw new SQLException("DB에 저장된 password가 잘못되었습니다.");
            } catch (IllegalUserNameException e) {
                throw new SQLException("DB에 저장된 userName이 잘못되었습니다.");
            } catch (IllegalEmailException e) {
                throw new SQLException("DB에 저장된 email이 잘못되었습니다.");
            }
        }
    }
}
