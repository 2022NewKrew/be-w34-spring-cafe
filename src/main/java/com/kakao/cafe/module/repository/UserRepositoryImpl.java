package com.kakao.cafe.module.repository;

import com.kakao.cafe.infra.exception.NoSuchDataException;
import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.repository.mapper.UserDBMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper = new UserDBMapper();

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO USERS (user_id, password, name, email) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            return preparedStatement;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT * FROM USERS", rowMapper);
    }

    @Override
    public User findUserById(Long id) {
        return jdbcTemplate.query("SELECT * FROM USERS WHERE id = ?", rowMapper, id).stream().findAny()
                .orElseThrow(() -> new NoSuchDataException("해당하는 사용자가 없습니다."));
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return jdbcTemplate.query("SELECT * FROM USERS WHERE name = ?", rowMapper, name).stream().findAny();
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE USERS SET password = ?, name = ?, email = ? WHERE id = ?",
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getId());
    }
}
