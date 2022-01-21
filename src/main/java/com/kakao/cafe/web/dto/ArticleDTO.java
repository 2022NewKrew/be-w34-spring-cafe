package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Comments;
import com.kakao.cafe.domain.Delete;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {

  private Long id;
  private UserDTO author;
  private String title;
  private String content;
  private CommentsDTO comments;
  private int numberOfComment;
  private Long readCount;
  private Delete isDeleted;
  private Timestamp createAt;
  private Timestamp modifiedAt;


  public ArticleDTO(Article article) {
    this.id = article.getId();
    this.author = new UserDTO(article.getAuthor());
    this.title = article.getTitle();
    this.content = article.getContent();
    this.numberOfComment = article.getComments().size();
    this.comments = new CommentsDTO(article.getComments());
    this.readCount = article.getReadCount();
    this.isDeleted = article.getIsDeleted();
    this.createAt = article.getCreateAt();
    this.modifiedAt = article.getModifiedAt();
  }


  public ArticleDTO(Long userId, String title, String content) {
    this.title = title;
    this.content = content;
    this.comments = new CommentsDTO();
    this.author = new UserDTO(userId);
    this.readCount = 0L;
    this.isDeleted = Delete.NOT_DELETED;
    this.createAt = new Timestamp(System.currentTimeMillis());
    this.modifiedAt = new Timestamp(System.currentTimeMillis());
  }


  @Override
  public String toString() {
    return "ArticleDTO{" +
        "id=" + id +
        ", author=" + author +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", comments=" + comments +
        ", readCount=" + readCount +
        ", isDeleted=" + isDeleted +
        ", createAt=" + createAt +
        ", modifiedAt=" + modifiedAt +
        '}';
  }

}
