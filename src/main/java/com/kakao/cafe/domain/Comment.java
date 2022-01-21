package com.kakao.cafe.domain;

import com.kakao.cafe.exception.CannotDeleteException;
import com.kakao.cafe.exception.NoRequiredValueException;
import com.kakao.cafe.web.dto.CommentDTO;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

  private Long id;
  // Many To One - 단방향
  private User author;
  private Long articleId;
  private String contents;
  private Long likeCount;
  private Delete isDeleted;
  private Timestamp createAt;
  private Timestamp modifiedAt;


  public static Comment of(CommentDTO commentDTO) {

    String contents = commentDTO.getContents();

    if(StringUtils.isBlank(contents)) {
      throw new NoRequiredValueException();
    }

    return new Comment(
        commentDTO.getId(),
        User.create(commentDTO.getAuthor()),
        commentDTO.getArticleId(),
        commentDTO.getContents(),
        commentDTO.getLikeCount(),
        commentDTO.getIsDeleted(),
        commentDTO.getCreateAt(),
        commentDTO.getModifiedAt()
    );
  }


  public static Comment of(Long id, Comment comment) {
    return new Comment(
        id,
        comment.getAuthor(),
        comment.getArticleId(),
        comment.getContents(),
        comment.getLikeCount(),
        comment.getIsDeleted(),
        comment.getCreateAt(),
        comment.getModifiedAt()
    );
  }


  public static Comment create(
      Long id, User author, Long articleId,
      String contents, Long likeCount, Delete isDeleted,
      Timestamp createAt, Timestamp modifiedAt
  ) {
    return new Comment(
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


  public void delete(Delete deleteLevel) {

    if(isDeleted.ordinal() >= deleteLevel.ordinal()) {
      throw new CannotDeleteException();
    }

    isDeleted = deleteLevel;
  }


  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", author=" + author +
        ", articleId=" + articleId +
        ", contents='" + contents + '\'' +
        ", likeCount=" + likeCount +
        ", isDeleted=" + isDeleted +
        ", createAt=" + createAt +
        ", modifiedAt=" + modifiedAt +
        '}';
  }

}
