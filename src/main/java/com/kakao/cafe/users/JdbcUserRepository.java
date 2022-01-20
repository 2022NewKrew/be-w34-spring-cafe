package com.kakao.cafe.users;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@PropertySource("classpath:sql/user.xml")
public class JdbcUserRepository implements UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${user.save}")
    private String SAVE_SQL;
    @Value("${user.findByUserId}")
    private String FIND_BY_USER_ID_SQL;
    @Value("${user.findAll}")
    private String FIND_ALL_SQL;
    @Value("${user.findById}")
    private String FIND_BY_ID_SQL;

    private static final RowMapper<User> rowMapper = (resultSet, rowNum) -> new User(
            resultSet.getLong("id"),
            resultSet.getString("user_id"),
            resultSet.getString("name"),
            resultSet.getString("password"),
            resultSet.getString("email")
    );

    public JdbcUserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        Map<String, Object> params = new HashMap<>();

        params.put("user_id", user.getUserId());
        params.put("password", user.getPassword());
        params.put("email", user.getEmail());
        params.put("name", user.getName());

        jdbcTemplate.update(SAVE_SQL, params);

        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID_SQL, params, rowMapper));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);

        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_USER_ID_SQL, params, rowMapper));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, rowMapper);
    }
}
