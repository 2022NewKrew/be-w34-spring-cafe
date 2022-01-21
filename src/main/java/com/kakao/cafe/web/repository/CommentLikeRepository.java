package com.kakao.cafe.web.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentLikeRepository {

  private static final Logger logger = LoggerFactory.getLogger(CommentLikeRepository.class);
  private final JdbcTemplate jdbcTemplate;


  public CommentLikeRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public void save(Long commentId, Long userId) {
    String query = "INSERT INTO comment_like ("
        + "prefix_id, "
        + "comment_id, "
        + "user_id "
        + ") VALUES (?, ?, ?)";

    jdbcTemplate.update(query,
        commentId + "_" + userId,
        commentId,
        userId
    );

  }

}
