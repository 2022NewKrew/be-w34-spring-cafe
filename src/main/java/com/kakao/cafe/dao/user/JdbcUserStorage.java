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
        return jdbcTemplate.query(UserSql.getAllUser(), (rs, rowNum) -> toUser(rs));
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update(UserSql.insert(user));
    }

    @Override
    public Optional<User> findUserById(UserId userId) {
        return jdbcTemplate
                .query(UserSql.findUserById(userId.getValue()), (rs, rowNum) -> toUser(rs))
                .stream()
                .findFirst();
    }

    @Override
    public int getSize() {
        return jdbcTemplate.queryForObject(UserSql.count(), Integer.class);
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(UserSql.update(user));
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
