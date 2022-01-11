package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import java.sql.Timestamp;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public User save(User user) {
    String query = "INSERT INTO USERS (email, nick_name, password) VALUES (?,?,?);";
    jdbcTemplate.update(query, user.getEmail(), user.getNickName(), user.getPassword());
    return user;
  }

  public Users findAll() {
    String query = "SELECT * FROM USERS;";
    List<User> users = jdbcTemplate.query(query, (rs, rowNum) -> {
      String email = rs.getString("EMAIL");
      String nickName = rs.getString("NICK_NAME");
      String password = rs.getString("PASSWORD");
      Timestamp createAt = rs.getTimestamp("CREATE_AT");
      Timestamp lastLoginAt = rs.getTimestamp("LAST_LOGIN_AT");
      return User.of(rowNum + 1, email, nickName, password, createAt, lastLoginAt);
    });
    return Users.of(users);
  }

}
