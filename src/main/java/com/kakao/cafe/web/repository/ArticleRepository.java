package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticlePage;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.web.repository.UserRepository.UserMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {

  public static final Logger logger = LoggerFactory.getLogger(ArticleRepository.class);
  private final JdbcTemplate jdbcTemplate;
  private final ArticleMapper articleMapper;

  public ArticleRepository(JdbcTemplate jdbcTemplate, ArticleMapper articleMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.articleMapper = articleMapper;
  }

  public Integer totalSize() {
    String query = "SELECT COUNT(*) FROM ARTICLE";
    return jdbcTemplate.queryForObject(query, Integer.class);
  }

  //TODO: 페이징 최적화 방법 더 고민하고 연구해보기
  public ArticlePage findByOffset(int offset, int limit) {
    String query =
        "SELECT "
            + "article.id, "
            + "article.title, "
            + "article.content, "
            + "article.read_count, "
            + "article.create_at, "
            + "article.modified_at, "
            + "users.id as user_id, "
            + "users.email as user_email, "
            + "users.nick_name as user_nick_name, "
            + "users.summary as user_summary, "
            + "users.profile as user_profile, "
            + "users.create_at as user_create_at, "
            + "users.modified_at as user_modified_at, "
            + "users.last_login_at as user_last_login_at "
            + "FROM (SELECT * FROM article ORDER BY id DESC LIMIT ?, ?) article "
            + "INNER JOIN USERS "
            + "ON article.user_id = users.id";

    List<Article> articles = jdbcTemplate.query(query, articleMapper, offset, limit);
    return ArticlePage.of(articles);
  }


  @Component
  public static class ArticleMapper implements RowMapper<Article> {

    private final UserMapper userMapper;

    public ArticleMapper(UserMapper userMapper) {
      this.userMapper = userMapper;
    }

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

      // article mapping
      Long id = rs.getLong("ID");
      String title = rs.getString("TITLE");
      String content = rs.getString("CONTENT");
      Long readCount = rs.getLong("READ_COUNT");
      Timestamp createAt = rs.getTimestamp("CREATE_AT");
      Timestamp modified_at = rs.getTimestamp("MODIFIED_AT");

      // user mapping
      User author = userMapper.mapRowExternal(rs, rowNum);

      return Article.create(
          id, author, title, content, readCount,
          new ArrayList<>(), createAt, modified_at
      );
    }
  }

}
