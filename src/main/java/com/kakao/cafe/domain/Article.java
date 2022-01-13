package com.kakao.cafe.domain;

import java.sql.Timestamp;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Article {

  private final Long id;
  private final User author;
  private String title;
  private String content;
  private Long readCount;
  private List<Comment> comments;
  private Timestamp createAt;
  private Timestamp modifiedAt;

  public static Article create(
      Long id, User author, String title,
      String content, Long readCount, List<Comment> comments,
      Timestamp createAt, Timestamp modifiedAt
  ) {
    return new Article(
        id, author, title, content, readCount,
        comments, createAt, modifiedAt
    );
  }

  public void addReadCount() {
    readCount++;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", author=" + author +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", readCount=" + readCount +
        ", comments=" + comments +
        ", createAt=" + createAt +
        ", modifiedAt=" + modifiedAt +
        '}';
  }
}
