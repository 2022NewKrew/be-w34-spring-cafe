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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcUserInfoRepository implements UserInfoRepository {

    private static final String SELECT_ALL = "select * from user";

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserInfoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("user");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getUserId());
        parameters.put("password", user.getPassword());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());

        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public void update(User user) {
        String sql = "update user set name=?, email=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getUserId());
    }

    @Override
    public List<User> getAllUserList() {
        return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> new UserMapper().mapRow(rs, rowNum));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String sql = SELECT_ALL + " where id = ?";
        User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new UserMapper().mapRow(rs, rowNum), userId);
        return Optional.ofNullable(user);
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                return new User.Builder().userId(rs.getString("id"))
                                         .password(rs.getString("password"))
                                         .name(rs.getString("name"))
                                         .email(rs.getString("email"))
                                         .build();
            } catch (IllegalUserIdException | IllegalPasswordException | IllegalUserNameException | IllegalEmailException e) {
                throw new SQLException("DB에서 값을 읽어오지 못했습니다.");
            }
        }
    }
}
