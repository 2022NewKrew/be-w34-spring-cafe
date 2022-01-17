package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class UserDBRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long persist(User user) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> makePersistStatement(conn, user), generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

    @Override
    public Optional<User> find(Long id) {
        User user = jdbcTemplate.queryForObject(SQL.FIND_BY_DB_ID.stmt, this::convertToUser, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> find(String stringId) {
        User user = jdbcTemplate.queryForObject(SQL.FIND_BY_STRING_ID.stmt, this::convertToUser, stringId);
        return Optional.ofNullable(user);
    }

    @Override
    public ArrayList<User> findAll() {
        List<User> users = jdbcTemplate.query(SQL.FIND_ALL.stmt, this::convertToUser);
        return new ArrayList<User>(users);
    }

    @Override
    public void updateUserInfo(User user) {
        jdbcTemplate.update(SQL.UPDATE_BY_STRING_ID.stmt, user.getPassword(), user.getName(), user.getEmail(), user.getStringId());
    }

    private User convertToUser(ResultSet rs, int rowNum) throws SQLException {
        User user = User.builder()
                .id(rs.getLong("id"))
                .stringId(rs.getString("string_id"))
                .email(rs.getString("email"))
                .name(rs.getString("name"))
                .password(rs.getString("password"))
                .signUpDate(rs.getTimestamp("sign_up_date").toLocalDateTime())
                .build();

        return user;
    }

    private PreparedStatement makePersistStatement(Connection conn, User user) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(SQL.CREATE.stmt, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getStringId());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getName());
        statement.setString(4, user.getEmail());
        statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
        return statement;
    }

    private enum SQL {
        FIND_BY_STRING_ID("SELECT * FROM USERS WHERE string_id = ?"),
        FIND_BY_DB_ID("SELECT * FROM USERS WHERE ID = ?"),
        FIND_ALL("SELECT * FROM USERS"),
        UPDATE_BY_STRING_ID("UPDATE USERS SET password = ?, name = ?, email = ? WHERE string_id = ?"),
        CREATE("INSERT INTO USERS (string_id, password, name, email, sign_up_date) VALUES (?, ?, ?, ?, ?)");

        public final String stmt;

        SQL(String stmt) {
            this.stmt = stmt;
        }
    }
}
