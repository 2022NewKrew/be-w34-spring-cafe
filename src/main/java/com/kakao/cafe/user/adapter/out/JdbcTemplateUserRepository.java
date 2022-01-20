package com.kakao.cafe.user.adapter.out;

import com.kakao.cafe.user.application.port.out.CreateUserDto;
import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.application.port.out.SaveUserPort;
import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateUserRepository implements SaveUserPort, LoadUserPort {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper;


    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userMapper = userRowMapper();
    }

    @Override
    public UserId save(CreateUserDto createUserDto) {
        final String INSERT_USER_SQL = "INSERT INTO `USER` (email, nickname, password) VALUES (?, ?, ? )";

        PreparedStatementCreatorFactory creatorFactory = new PreparedStatementCreatorFactory(
            INSERT_USER_SQL, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);
        creatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = creatorFactory.newPreparedStatementCreator(
            Arrays.asList(createUserDto.getEmail(), createUserDto.getNickname(),
                createUserDto.getPassword()));

        return new UserId(jdbcTemplate.update(psc));
    }

    @Override
    public Optional<User> loadById(UserId userId) {
        final String LOAD_USER_SQL = "SELECT id, email, nickname, password FROM `USER` WHERE id=?";

        User user = jdbcTemplate.queryForObject(LOAD_USER_SQL, userMapper, userId.getId());
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> loadByEmail(Email email) {
        final String LOAD_USER_BY_EMAIL_SQL = "SELECT id, email, nickname, password FROM `USER` WHERE email=?";

        User user = jdbcTemplate.queryForObject(LOAD_USER_BY_EMAIL_SQL,
            userMapper, email.getValue());
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> loadAll() {
        final String LOAD_ALL_USERS_SQL = "SELECT id, email, nickname, password FROM `USER`";

        return jdbcTemplate.query(LOAD_ALL_USERS_SQL, userMapper);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            UserId userId = new UserId(rs.getString("id"));
            Email email = new Email(rs.getString("email"));
            String nickname = rs.getString("nickname");
            Password password = new Password(rs.getString("password"));

            return new User(userId, email, nickname, password);
        };
    }
}
