package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Comment;
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
  private String title;
  private String content;
  private UserDTO author;
  private Long readCount;
  private List<CommentDTO> comments;
  private Timestamp createAt;
  private Timestamp modifiedAt;

  public ArticleDTO(Article article) {
    this.id = article.getId();
    this.title = article.getTitle();
    this.content = article.getContent();
    this.author = new UserDTO(article.getAuthor());
    this.readCount = article.getReadCount();
    this.comments = fromDomain(article.getComments());
    this.createAt = article.getCreateAt();
    this.modifiedAt = article.getModifiedAt();
  }

  public ArticleDTO(Long userId, String title, String content) {
    this.title = title;
    this.content = content;
    this.author = new UserDTO(userId);
    this.readCount = 0L;
    this.comments = new ArrayList<>();
    this.createAt = new Timestamp(System.currentTimeMillis());
    this.modifiedAt = new Timestamp(System.currentTimeMillis());
  }

  private List<CommentDTO> fromDomain(List<Comment> comments) {
    return comments.stream()
        .map(CommentDTO::new)
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "ArticleDTO{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", author=" + author +
        ", readCount=" + readCount +
        ", comments=" + comments +
        ", createAt=" + createAt +
        ", modifiedAt=" + modifiedAt +
        '}';
  }
}
