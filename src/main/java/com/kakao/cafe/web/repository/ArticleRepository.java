package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticlePage;
import com.kakao.cafe.domain.Comments;
import com.kakao.cafe.domain.Delete;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.web.repository.CommentRepository.CommentMapper;
import com.kakao.cafe.web.repository.UserRepository.UserMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
public class ArticleRepository {

  public static final Logger logger = LoggerFactory.getLogger(ArticleRepository.class);
  private final JdbcTemplate jdbcTemplate;
  private final ArticleMapper articleMapper;

  public ArticleRepository(JdbcTemplate jdbcTemplate, ArticleMapper articleMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.articleMapper = articleMapper;
  }


  public Article save(Article article) {
    if (isNew(article)) {
      return persist(article);
    }
    return merge(article);
  }


  private boolean isNew(Article article) {
    return findById(article.getId(), Delete.COMPLETELY_DELETED).isEmpty();
  }


  private Article persist(Article article) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    String query = "INSERT INTO article ( "
        + "user_id, "
        + "title, "
        + "content, "
        + "read_count, "
        + "create_at, "
        + "modified_at "
        + ") VALUES (?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(con -> {
      PreparedStatement ps = con.prepareStatement(query,
          new String[]{"id"});
      ps.setLong(1, article.getAuthor().getId());
      ps.setString(2, article.getTitle());
      ps.setString(3, article.getContent());
      ps.setLong(4, article.getReadCount());
      ps.setTimestamp(5, article.getCreateAt());
      ps.setTimestamp(6, article.getModifiedAt());
      return ps;
    }, keyHolder);

    Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
    return Article.of(generatedId, article);
  }


  private Article merge(Article article) {
    String query = "UPDATE ARTICLE "
        + "SET "
        + "title = ?, "
        + "content = ?, "
        + "read_count = ?, "
        + "is_deleted = ?, "
        + "create_at = ?, "
        + "modified_at = now() "
        + "WHERE id = ?";

    jdbcTemplate.update(query,
        article.getTitle(),
        article.getContent(),
        article.getReadCount(),
        article.getIsDeleted().name(),
        article.getCreateAt(),
        article.getId()
    );

    return article;
  }


  public Integer totalSize(Delete deleteLevel) {
    String query = "SELECT COUNT(*) "
        + "FROM ARTICLE "
        + "WHERE is_deleted <= ?";
    return jdbcTemplate.queryForObject(query, Integer.class, deleteLevel.ordinal());
  }


  //TODO: 페이징 최적화 방법 더 고민하고 연구해보기
  public ArticlePage findByOffset(int offset, int limit, Delete deleteLevel) {
    String query = ArticleMapper.SELECT_ALL_COLUMNS
        + "FROM ("
        + "   SELECT * FROM article "
        + "   WHERE is_deleted <= ? "
        + "   ORDER BY id DESC "
        + "   LIMIT ?, ? "
        + "   ) article "
        + "INNER JOIN USERS "
        + "ON article.user_id = users.id "
        + "ORDER BY article.id DESC";

    List<Article> articles = jdbcTemplate.query(query, articleMapper,
        deleteLevel.ordinal(),
        offset,
        limit
    );
    return ArticlePage.of(articles);
  }


  public Optional<Article> findById(Long id, Delete deleteLevel) {
    String query = ArticleMapper.SELECT_ALL_COLUMNS
        + "FROM article "
        + "INNER JOIN users "
        + "ON article.user_id = users.id "
        + "AND article.id = ? "
        + "AND article.is_deleted <= ? ";

    List<Article> articles = jdbcTemplate.query(query, articleMapper,
        id,
        deleteLevel.ordinal()
    );

    if (articles.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(articles.get(0));
  }


  public Article updateReadCount(Article article) {
    String query = "UPDATE article "
        + "SET "
        + "read_count = ? "
        + "WHERE id = ? "
        + "AND is_deleted = ?";

    jdbcTemplate.update(query,
        article.getReadCount(),
        article.getId(),
        Delete.NOT_DELETED.name()
    );
    return article;
  }


  public int softDeleteById(Long id, Delete deleteLevel) {
    String query = "UPDATE article "
        + "SET "
        + "is_deleted = ? "
        + "WHERE id = ?";

    return jdbcTemplate.update(query,
        deleteLevel.name(),
        id
    );
  }


  @Component
  public static class ArticleMapper implements RowMapper<Article> {

    public static final String SELECT_ALL_COLUMNS =
        "SELECT "
            + "article.id, "
            + "article.title, "
            + "article.content, "
            + "article.read_count, "
            + "article.is_deleted, "
            + "article.create_at, "
            + "article.modified_at, "
            + UserMapper.SELECT_ALL_COLUMNS_EXTERNAL;
    ;

    private final UserMapper userMapper;
    private final CommentRepository commentRepository;

    public ArticleMapper(UserMapper userMapper, CommentRepository commentRepository) {
      this.userMapper = userMapper;
      this.commentRepository = commentRepository;
    }


    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

      // article mapping
      Long id = rs.getLong("ID");
      String title = rs.getString("TITLE");
      String content = rs.getString("CONTENT");
      Long readCount = rs.getLong("READ_COUNT");
      Delete isDeleted = Delete.valueOf(rs.getString("IS_DELETED"));
      Timestamp createAt = rs.getTimestamp("CREATE_AT");
      Timestamp modifiedAt = rs.getTimestamp("MODIFIED_AT");

      // user mapping
      User author = userMapper.mapRowExternal(rs, rowNum);
      Comments comments = commentRepository.findByArticleId(id, isDeleted);

      return Article.create(
          id,
          author,
          title,
          content,
          comments,
          readCount,
          isDeleted,
          createAt,
          modifiedAt
      );
    }

  }

}
