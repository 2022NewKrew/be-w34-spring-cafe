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
    public void addUser(UserId userId, Password password, Name name, Email email) {
        checkUser(userId);

        String query = "INSERT INTO USER_DATA(USER_ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                query,
                userId.getValue(),
                password.getValue(),
                name.getValue(),
                email.getValue()
        );
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
    public void update(UserId userId, Name name, Email email) {
        String query = "UPDATE USER_DATA SET NAME = ?, EMAIL = ? WHERE USER_ID = ?";
        jdbcTemplate.update(
                query,
                name.getValue(),
                email.getValue(),
                userId.getValue()
        );
    }

    private User toUser(ResultSet resultSet) throws SQLException {
        return new User(
                new UserId(resultSet.getString("USER_ID")),
                new Password(resultSet.getString("PASSWORD")),
                new Name(resultSet.getString("NAME")),
                new Email(resultSet.getString("EMAIL"))
        );
    }

    private void checkUser(UserId userId) {
        String query = String.format(
                "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USER_DATA WHERE USER_ID = '%s'",
                userId.getValue()
        );
        if (jdbcTemplate.query(query, (rs, rowNum) -> toUser(rs)).isEmpty()) {
            return;
        }
        throw new IllegalArgumentException("아이디가 중복됩니다!");
    }
}
