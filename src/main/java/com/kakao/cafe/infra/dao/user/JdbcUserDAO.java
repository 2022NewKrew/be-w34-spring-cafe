package com.kakao.cafe.infra.dao.user;

import com.kakao.cafe.infra.dao.UserLoginCommand;
import com.kakao.cafe.web.user.form.ModifyingUserInfo;
import com.kakao.cafe.web.user.form.UserProfileInfo;
import com.kakao.cafe.infra.dao.UserCreateCommand;
import com.kakao.cafe.infra.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcUserDAO implements UserDAO {
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE users IF EXISTS";
    private static final String USER_TABLE_DDL = "CREATE TABLE users ( " +
            "id BIGINT GENERATED BY DEFAULT AS IDENTITY, " +
            "nickname VARCHAR(255), " +
            "email VARCHAR(255), " +
            "name VARCHAR(255), " +
            "password VARCHAR(255), " +
            "created_time TIMESTAMP, " +
            "PRIMARY KEY(id) )";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM users";
    private static final String UPDATE_SQL = "UPDATE USERS SET name = ?," +
            " email = ?," +
            " password = ?" +
            " WHERE id = ?";
    private static final String FIND_USER_PROFILE = "SELECT nickname, email FROM users WHERE id = ?";
    private static final String FIND_LOGIN_INFO_BY_NICKNAME = "SELECT id, nickname, password FROM users WHERE nickname = ?";
    private static final String FIND_NICKNAME_BY_EXPECTED_NICKNAME = "SELECT nickname FROM users WHERE nickname = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(DROP_TABLE_IF_EXISTS);
        jdbcTemplate.execute(USER_TABLE_DDL);
    }

    @Override
    public void saveUser(com.kakao.cafe.infra.dao.UserCreateCommand userCreateCommandCreateCommander) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = assembleParameters(userCreateCommandCreateCommander);
        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    private Map<String, Object> assembleParameters(com.kakao.cafe.infra.dao.UserCreateCommand userCreateCommand) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nickname", userCreateCommand.getNickname());
        parameters.put("email", userCreateCommand.getEmail());
        parameters.put("name", userCreateCommand.getName());
        parameters.put("password", userCreateCommand.getPassword());
        parameters.put("created_time", Timestamp.valueOf(userCreateCommand.getCreatedTime()));
        return parameters;
    }

    @Override
    public Optional<UserCreateCommand> findUserById(Long userId) {
        List<UserCreateCommand> result = jdbcTemplate.query(FIND_BY_ID_SQL, userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public List<UserCreateCommand> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, userRowMapper());
    }

    @Override
    public void updateUser(Long userId, String name, String email, String password) {
        jdbcTemplate.update(UPDATE_SQL, name, email, password, userId);
    }

    private RowMapper<UserCreateCommand> userRowMapper() {
        return (result, rowNum) -> new UserCreateCommand(
                result.getLong("id"),
                result.getTimestamp("created_time").toLocalDateTime(),
                result.getString("nickname"),
                result.getString("email"),
                result.getString("name"),
                result.getString("password")
        );
    }

    @Override
    public Optional<UserProfileInfo> findUserProfileInfo(Long userId) {
        return jdbcTemplate.query(FIND_USER_PROFILE, userProfileRowMapper(), userId).stream().findAny();
    }

    private RowMapper<UserProfileInfo> userProfileRowMapper() {
        return ((rs, rowNum) -> new UserProfileInfo(
                rs.getString("nickname"),
                rs.getString("email")
        ));
    }

    @Override
    public Optional<ModifyingUserInfo> findModifyingUserForm(Long userId) {
        return jdbcTemplate.query(FIND_BY_ID_SQL, modifyingUserFormRowMapper(),  userId)
                .stream().findAny();
    }

    private RowMapper<ModifyingUserInfo> modifyingUserFormRowMapper() {
        return (rs, rowNum) -> new ModifyingUserInfo(
                rs.getString("nickname"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );
    }

    /**
     * To-do
     */
    @Override
    public Optional<UserLoginCommand> findPasswordAndIdByNickname(String nickname) {
        return Optional.empty();
    }


    @Override
    public Optional<String> findNicknameByExpectedNickname(String nickname) {
        return jdbcTemplate.query(FIND_NICKNAME_BY_EXPECTED_NICKNAME, nicknameRowMapper(), nickname).stream().findAny();
    }

    private RowMapper<String> nicknameRowMapper() {
        return ((rs, rowNum) -> rs.getString("nickname"));
    }
}
