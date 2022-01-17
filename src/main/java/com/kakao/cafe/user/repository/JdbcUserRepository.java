package com.kakao.cafe.user.repository;

import com.kakao.cafe.exception.UserDuplicatedException;
import com.kakao.cafe.user.domain.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String INSERT_QUERY = "insert into users(userId, password, name, email) values(?, ?, ?, ?)";
    private static final String SELECT_QUERY = "select * from users where userId=?";
    private static final String SELECT_ALL_QUERY = "select * from users";
    private static final String DELETE_ALL_QUERY = "delete from users where true";

    @Override
    public void save(User user) throws UserDuplicatedException {
        try {
            jdbcTemplate.update(
                INSERT_QUERY,
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
        } catch (DuplicateKeyException e) {
            throw new UserDuplicatedException();
        }
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> users = jdbcTemplate.query(SELECT_QUERY, mapper, userId);
        if (users.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, mapper);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL_QUERY);
    }

    static RowMapper<User> mapper = (rs, rowNum) ->
        User.builder()
            .userId(rs.getString("userId"))
            .email(rs.getString("email"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .build();
}
