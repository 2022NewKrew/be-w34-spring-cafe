package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
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

}
