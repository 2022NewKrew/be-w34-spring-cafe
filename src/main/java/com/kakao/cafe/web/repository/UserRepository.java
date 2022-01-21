package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
  private final JdbcTemplate jdbcTemplate;
  private final UserMapper userMapper;


  public UserRepository(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.userMapper = userMapper;
  }


  public User save(User user) {
    if (isNew(user)) {
      return persist(user);
    }
    return merge(user);
  }


  private boolean isNew(User user) {
    return findById(user.getId()).isEmpty();
  }


  private User persist(User user) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    String query = "INSERT INTO users ("
        + "email, "
        + "nick_name, "
        + "summary, "
        + "profile, "
        + "password, "
        + "create_at, "
        + "modified_at, "
        + "last_login_at"
        + ") VALUES (?,?,?,?,?,?,?,?);";

    jdbcTemplate.update(con -> {
      PreparedStatement ps = con.prepareStatement(query,
          new String[]{"id"});   // auto-increment key holder
      ps.setString(1, user.getEmail().toString());
      ps.setString(2, user.getNickName());
      ps.setString(3, user.getSummary());
      ps.setString(4, user.getProfile());
      ps.setString(5, user.getPassword());
      ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
      ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
      ps.setTimestamp(8, null);
      return ps;
    }, keyHolder);

    Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
    return User.create(generatedId, user);
  }


  private User merge(User user) {
    String query = "UPDATE USERS "
        + "SET "
        + "email = ?, "
        + "nick_name = ?, "
        + "summary = ?, "
        + "profile = ?, "
        + "password = ?, "
        + "create_at = ?, "
        + "modified_at = now(),"
        + "last_login_at = ? "
        + "WHERE id = ?";

    jdbcTemplate.update(query,
        user.getEmail().toString(),
        user.getNickName(),
        user.getSummary(),
        user.getProfile(),
        user.getPassword(),
        user.getCreateAt(),
        user.getLastLoginAt(),
        user.getId()
    );

    return user;
  }


  public Users findAll() {
    String query = UserMapper.SELECT_ALL_COLUMNS
        + "FROM USERS "
        + "ORDER BY create_at, email";

    List<User> users = jdbcTemplate.query(query, userMapper);

    return Users.of(users);
  }


  public Optional<User> findById(Long id) {
    String query = UserMapper.SELECT_ALL_COLUMNS
        + "FROM USERS "
        + "WHERE id = ?";

    List<User> user = jdbcTemplate.query(query, userMapper, id);

    if (user.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(user.get(0));
  }


  public Optional<User> findByEmail(String email) {
    String query = UserMapper.SELECT_ALL_COLUMNS
        + "FROM USERS "
        + "WHERE email = ?";

    List<User> user = jdbcTemplate.query(query, userMapper, email);

    if (user.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(user.get(0));
  }


  @Component
  public static class UserMapper implements RowMapper<User> {

    public static final String SELECT_ALL_COLUMNS =
        "SELECT "
            + "id, "
            + "email, "
            + "nick_name, "
            + "summary, "
            + "profile, "
            + "password, "
            + "create_at, "
            + "modified_at, "
            + "last_login_at ";

    public static final String SELECT_ALL_COLUMNS_EXTERNAL =
        " users.id as user_id, "
            + "users.email as user_email, "
            + "users.nick_name as user_nick_name, "
            + "users.summary as user_summary, "
            + "users.profile as user_profile, "
            + "users.create_at as user_create_at, "
            + "users.modified_at as user_modified_at, "
            + "users.last_login_at as user_last_login_at ";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

      Long id = rs.getLong("ID");
      String email = rs.getString("EMAIL");
      String nickName = rs.getString("NICK_NAME");
      String summary = rs.getString("SUMMARY");
      String profile = rs.getString("PROFILE");
      String password = rs.getString("PASSWORD");
      Timestamp createAt = rs.getTimestamp("CREATE_AT");
      Timestamp modifiedAt = rs.getTimestamp("MODIFIED_AT");
      Timestamp lastLoginAt = rs.getTimestamp("LAST_LOGIN_AT");

      return User.create(
          rowNum + 1,
          id,
          email,
          nickName,
          summary,
          profile,
          password,
          createAt,
          modifiedAt,
          lastLoginAt
      );
    }


    public User mapRowExternal(ResultSet rs, int rowNum) throws SQLException {

      Long id = rs.getLong("USER_ID");
      String email = rs.getString("USER_EMAIL");
      String nickName = rs.getString("USER_NICK_NAME");
      String summary = rs.getString("USER_SUMMARY");
      String profile = rs.getString("USER_PROFILE");
      Timestamp createAt = rs.getTimestamp("USER_CREATE_AT");
      Timestamp modifiedAt = rs.getTimestamp("USER_MODIFIED_AT");
      Timestamp lastLoginAt = rs.getTimestamp("USER_LAST_LOGIN_AT");

      return User.create(
          rowNum + 1,
          id,
          email,
          nickName,
          summary,
          profile,
          null,
          createAt,
          modifiedAt,
          lastLoginAt
      );
    }

  }

}
