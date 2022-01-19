package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.user.Email;
import com.kakao.cafe.model.user.Name;
import com.kakao.cafe.model.user.Password;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserId;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JdbcUserStorage implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        String query = "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USER_DATA";
        return jdbcTemplate.query(query, (rs, rowNum) -> toUser(rs));
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO USER_DATA(USER_ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                query,
                user.getUserId().getValue(),
                user.getPassword().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue());
    }

    @Override
    public Optional<User> findUserById(UserId userId) {
        String query = String.format(
                "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USER_DATA WHERE USER_ID = '%s'",
                userId.getValue()
        );
        return jdbcTemplate
                .query(query, (rs, rowNum) -> toUser(rs))
                .stream()
                .findFirst();
    }

    @Override
    public int getSize() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER_DATA", Integer.class);
    }

    @Override
    public void update(User user) {
        String query = "UPDATE USER_DATA SET NAME = ?, EMAIL = ? WHERE USER_ID = ?";
        jdbcTemplate.update(
                query,
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getUserId().getValue());
    }

    private User toUser(ResultSet resultSet) throws SQLException {
        return new User(
                new UserId(resultSet.getString("USER_ID")),
                new Password(resultSet.getString("PASSWORD")),
                new Name(resultSet.getString("NAME")),
                new Email(resultSet.getString("EMAIL"))
        );
    }
}
