package com.kakao.cafe.domain.user.dao;

import com.kakao.cafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(User user) {
        final String sql = "INSERT INTO USERS (EMAIL, PASSWORD, NICKNAME) VALUES(?, ?, ?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});

            preparedStatement.setObject(1, user.getEmail());
            preparedStatement.setObject(2, user.getPassword());
            preparedStatement.setObject(3, user.getNickname());

            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(User user) {
        final String sql = "UPDATE USERS SET NICKNAME = ? WHERE ID = ? AND IS_DELETED IS false";
        jdbcTemplate.update(sql, user.getNickname(), user.getId());
    }

    public Optional<User> findById(Long id) {
        final String sql = "SELECT ID, EMAIL, PASSWORD, NICKNAME, CREATED_AT FROM USERS WHERE ID = ? AND IS_DELETED IS FALSE";
        List<User> result = jdbcTemplate.query(sql, userRowMapper(), id);
        return result.stream().findAny();
    }

    public Optional<User> findByEmail(String email) {
        final String sql = "SELECT ID, EMAIL, PASSWORD, NICKNAME, CREATED_AT FROM USERS WHERE EMAIL = ? AND IS_DELETED IS FALSE";
        List<User> result = jdbcTemplate.query(sql, userRowMapper(), email);
        return result.stream().findAny();
    }

    public List<User> findAll() {
        final String sql = "SELECT ID, EMAIL, PASSWORD, NICKNAME, CREATED_AT FROM USERS WHERE IS_DELETED IS FALSE";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    public boolean checkEmailRedundancy(String email) {
        final String sql = "SELECT EXISTS(SELECT * FROM USERS WHERE EMAIL = ? AND IS_DELETED IS FALSE)";
        final Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, email);
        Objects.requireNonNull(result);
        return result;
    }

    private RowMapper<User> userRowMapper() {
        return (resultSet, rowNum) ->
                (resultSet.wasNull()) ? null : User.builder()
                                                    .id(resultSet.getLong("id"))
                                                    .email(resultSet.getString("email"))
                                                    .password(resultSet.getString("password"))
                                                    .nickname(resultSet.getString("nickname"))
                                                    .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                                                    .build();
    }
}
