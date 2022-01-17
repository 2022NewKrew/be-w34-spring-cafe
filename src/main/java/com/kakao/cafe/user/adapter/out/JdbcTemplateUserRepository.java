package com.kakao.cafe.user.adapter.out;

import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.application.port.out.SaveUserPort;
import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateUserRepository implements SaveUserPort, LoadUserPort {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper;


    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userMapper = userRowMapper();
    }

    @Override
    public void save(User user) {
        UUID userId = user.getUserId().getUUID();
        String email = user.getEmail().getValue();
        String nickname = user.getNickname();
        String password = user.getPassword();

        jdbcTemplate.update(
            "INSERT INTO `USER` (id, email, nickname, password) VALUES (?, ?, ?, ? )",
            userId, email, nickname, password);
    }

    @Override
    public Optional<User> load(UserId userId) {
        User user = jdbcTemplate.queryForObject(
            "SELECT id, email, nickname, password FROM `USER` WHERE id=?",
            userMapper, userId.getUUID());
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> loadAll() {
        return jdbcTemplate.query("SELECT id, email, nickname, password FROM `USER`", userMapper);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            UserId userId = new UserId(rs.getString("id"));
            Email email = new Email(rs.getString("email"));
            String nickname = rs.getString("nickname");
            String password = rs.getString("password");

            return new User(userId, email, nickname, password);
        };
    }
}
