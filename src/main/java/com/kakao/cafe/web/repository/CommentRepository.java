package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Comments;
import com.kakao.cafe.domain.Delete;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.web.repository.UserRepository.UserMapper;
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
public class CommentRepository {

  private static final Logger logger = LoggerFactory.getLogger(CommentRepository.class);
  private final JdbcTemplate jdbcTemplate;
  private final CommentMapper commentMapper;


  public CommentRepository(JdbcTemplate jdbcTemplate, CommentMapper commentMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.commentMapper = commentMapper;
  }


  public Comment save(Comment comment) {
    if (isNew(comment)) {
      return persist(comment);
    }
    return merge(comment);
  }


  private boolean isNew(Comment comment) {
    return findById(comment.getId(), Delete.COMPLETELY_DELETED).isEmpty();
  }


  private Comment persist(Comment comment) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    String query = "INSERT INTO comment ("
        + "user_id, "
        + "article_id, "
        + "content, "
        + "create_at, "
        + "modified_at "
        + ") VALUES (?, ?, ?, ?, ?)";

    jdbcTemplate.update(con -> {
      PreparedStatement ps = con.prepareStatement(query,
          new String[]{"id"});    // auto-increment key holder
      ps.setLong(1, comment.getAuthor().getId());
      ps.setLong(2, comment.getArticleId());
      ps.setString(3, comment.getContents());
      ps.setTimestamp(4, comment.getCreateAt());
      ps.setTimestamp(5, comment.getModifiedAt());
      return ps;
    }, keyHolder);

    Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
    return Comment.of(generatedId, comment);
  }


  private Comment merge(Comment comment) {
    String query = "UPDATE comment "
        + "SET "
        + "content = ?, "
        + "is_deleted = ?, "
        + "modified_at = now() "
        + "WHERE id = ?";

    jdbcTemplate.update(query,
        comment.getContents(),
        comment.getIsDeleted().name(),
        comment.getId()
    );

    return comment;
  }


  public Optional<Comment> findById(Long id, Delete deleteLevel) {
    String query = CommentMapper.SELECT_ALL_COLUMNS
        + "FROM comment "
        + "INNER JOIN users "
        + "ON comment.user_id = users.id "
        + "AND comment.id = ? "
        + "AND comment.is_deleted <= ? "
        + "LEFT JOIN comment_like "
        + "ON comment.id = comment_like.comment_id "
        + "GROUP BY id";

    List<Comment> comments = jdbcTemplate.query(query, commentMapper,
        id,
        deleteLevel.ordinal()
    );

    if (comments.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(comments.get(0));
  }


  public Comments findByArticleId(Long articleId, Delete deleteLevel) {
    String query = CommentMapper.SELECT_ALL_COLUMNS
        + "FROM comment "
        + "INNER JOIN users "
        + "ON comment.user_id = users.id "
        + "AND comment.article_id = ? "
        + "AND comment.is_deleted <= ? "
        + "LEFT JOIN comment_like "
        + "ON comment.id = comment_like.comment_id "
        + "GROUP BY id";

    List<Comment> comments = jdbcTemplate.query(query, commentMapper,
        articleId,
        deleteLevel.ordinal()
    );

    return Comments.of(comments);
  }


  @Component
  public static class CommentMapper implements RowMapper<Comment> {

    public static final String SELECT_ALL_COLUMNS =
        "SELECT "
            + "comment.id,"
            + "comment.article_id, "
            + "comment.content, "
            + "count(comment_like.prefix_id) as like_count, "
            + "comment.is_deleted, "
            + "comment.create_at, "
            + "comment.modified_at, "
            + UserMapper.SELECT_ALL_COLUMNS_EXTERNAL;

    private final UserMapper userMapper;

    public CommentMapper(UserMapper userMapper) {
      this.userMapper = userMapper;
    }


    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {

      // comment mapping
      Long id = rs.getLong("ID");
      Long articleId = rs.getLong("ARTICLE_ID");
      String contents = rs.getString("CONTENT");
      Long likeCount = rs.getLong("LIKE_COUNT");
      Delete isDeleted = Delete.valueOf(rs.getString("IS_DELETED"));
      Timestamp createAt = rs.getTimestamp("CREATE_AT");
      Timestamp modifiedAt = rs.getTimestamp("MODIFIED_AT");

      // user mapping
      User author = userMapper.mapRowExternal(rs, rowNum);

      return Comment.create(
          id,
          author,
          articleId,
          contents,
          likeCount,
          isDeleted,
          createAt,
          modifiedAt
      );
    }
  }


}
