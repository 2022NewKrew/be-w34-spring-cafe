package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Delete;
import com.kakao.cafe.utils.SessionUtils;
import java.sql.Time;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

  private Long id;
  private UserDTO author;
  private Long articleId;
  private String contents;
  private Long likeCount;
  private Delete isDeleted;
  private Timestamp createAt;
  private Timestamp modifiedAt;
  private boolean isOwned;


  public CommentDTO(Comment comment) {
    this.id = comment.getId();
    this.author = new UserDTO(comment.getAuthor());
    this.articleId = comment.getArticleId();
    this.contents = comment.getContents();
    this.likeCount = comment.getLikeCount();
    this.isDeleted = comment.getIsDeleted();
    this.createAt = comment.getCreateAt();
    this.modifiedAt = comment.getModifiedAt();
    this.isOwned = SessionUtils.isLoginUser(comment.getAuthor());
  }


  public CommentDTO(Long userId, Long articleId, String contents) {
    this.author = new UserDTO(userId);
    this.articleId = articleId;
    this.contents = contents;
    this.likeCount = 0L;
    this.isDeleted = Delete.NOT_DELETED;
    this.createAt = new Timestamp(System.currentTimeMillis());
    this.modifiedAt = new Timestamp(System.currentTimeMillis());
  }


  @Override
  public String toString() {
    return "CommentDTO{" +
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
