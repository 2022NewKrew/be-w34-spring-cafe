package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.exception.NoMatchingForUpdateException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    jdbcTemplate.update(query, user.getEmail(), user.getNickName(),
        user.getSummary(), user.getProfile(), user.getPassword(), user.getCreateAt(),
        user.getModifiedAt(), user.getLastLoginAt());
    return user;
  }

  @Transactional
  public User update(User user) {
    // 기존에 존재하는 데이터 없을 경우 예외 처리
    if (delete(user) == 0) {
      throw new NoMatchingForUpdateException();
    }
    save(user);
    return user;
  }

  public Users findAll() {
    String query = "SELECT * FROM USERS ORDER BY CREATE_AT, EMAIL;";
    List<User> users = jdbcTemplate.query(query, new UserMapper());
    return Users.of(users);
  }

  public Optional<User> findByEmail(String email) {
    String query = "SELECT * FROM USERS WHERE EMAIL = ?";
    List<User> user = jdbcTemplate.query(query, new UserMapper(), email);
    if(user.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(user.get(0));
  }

  public int delete(User user) {
    String query = "DELETE FROM USERS WHERE EMAIL = ?";
    return jdbcTemplate.update(query, user.getEmail());
  }

  public static class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      String email = rs.getString("EMAIL");
      String nickName = rs.getString("NICK_NAME");
      String summary = rs.getString("SUMMARY");
      String profile = rs.getString("PROFILE");
      String password = rs.getString("PASSWORD");
      Timestamp createAt = rs.getTimestamp("CREATE_AT");
      Timestamp modifiedAt = rs.getTimestamp("MODIFIED_AT");
      Timestamp lastLoginAt = rs.getTimestamp("LAST_LOGIN_AT");
      return User.create(rowNum + 1, email, nickName, summary, profile, password, createAt, modifiedAt,
          lastLoginAt);
    }
  }

}
