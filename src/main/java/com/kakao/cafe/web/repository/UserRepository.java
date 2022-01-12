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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public User save(User user) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    String query = "INSERT INTO USERS ("
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
      PreparedStatement ps = con.prepareStatement(query, new String[]{"id"});   // auto-increment key holder
      ps.setString(1, user.getEmail());
      ps.setString(2, user.getNickName());
      ps.setString(3, user.getSummary());
      ps.setString(4, user.getProfile());
      ps.setString(5, user.getPassword());
      ps.setTimestamp(6, user.getCreateAt());
      ps.setTimestamp(7, user.getModifiedAt());
      ps.setTimestamp(8, user.getLastLoginAt());
      return ps;
    }, keyHolder);

    Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
    return User.of(generatedId, user);
  }

  @Transactional
  public User update(User user) {
    String query = "UPDATE USERS "
        + "SET "
        + "email = ?, "
        + "nick_name = ?, "
        + "summary = ?, "
        + "profile = ?, "
        + "password = ?, "
        + "create_at = ?, "
        + "modified_at = ?, "
        + "last_login_at = ? "
        + "WHERE id = ?";
    jdbcTemplate.update(query, user.getEmail(), user.getNickName(),
        user.getSummary(), user.getProfile(), user.getPassword(), user.getCreateAt(),
        user.getModifiedAt(), user.getLastLoginAt(), user.getId());
    return user;
  }

  public Users findAll() {
    String query = "SELECT * FROM USERS ORDER BY CREATE_AT, EMAIL;";
    List<User> users = jdbcTemplate.query(query, new UserMapper());
    return Users.of(users);
  }

  public Optional<User> findById(Long id) {
    String query = "SELECT * FROM USERS WHERE id = ?";
    List<User> user = jdbcTemplate.query(query, new UserMapper(), id);
    if (user.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(user.get(0));
  }

  public Optional<User> findByEmail(String email) {
    String query = "SELECT * FROM USERS WHERE email = ?";
    List<User> user = jdbcTemplate.query(query, new UserMapper(), email);
    if(user.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(user.get(0));
  }

  public int delete(User user) {
    String query = "DELETE FROM USERS WHERE id = ?";
    return jdbcTemplate.update(query, user.getId());
  }

  public static class UserMapper implements RowMapper<User> {

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
      return User.create(rowNum + 1, id, email, nickName, summary, profile, password, createAt,
          modifiedAt,
          lastLoginAt);
    }
  }

}
