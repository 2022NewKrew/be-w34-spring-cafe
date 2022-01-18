package com.kakao.cafe.domain;

import com.kakao.cafe.web.dto.ArticleDTO;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Article {

  private static final int INITIAL_READ_COUNT = 0;

  private final Long id;
  private final User author;
  private String title;
  private String content;
  private Long readCount;
  private Delete isDeleted;
  private List<Comment> comments;
  private Timestamp createAt;
  private Timestamp modifiedAt;


  /**
   * TODO:
   *
   * @param articleDTO
   * @return
   */
  public static Article of(ArticleDTO articleDTO) {
    return new Article(
        articleDTO.getId(),
        User.create(articleDTO.getAuthor()),
        articleDTO.getTitle(),
        articleDTO.getContent(),
        articleDTO.getReadCount(),
        articleDTO.getIsDeleted(),
        articleDTO.getComments()
            .stream()
            .map(Comment::of)
            .collect(Collectors.toList()),
        articleDTO.getCreateAt(),
        articleDTO.getModifiedAt()
    );
  }


  public static Article of(Long id, Article article) {
    return new Article(
        id,
        article.author,
        article.getTitle(),
        article.getContent(),
        article.getReadCount(),
        article.getIsDeleted(),
        article.getComments(),
        article.getCreateAt(),
        article.getModifiedAt()
    );
  }


  /**
   * 변경전 게시물에서 변경할 title, content 정보를 통해 새 게시물 생성
   *
   * @param article 변경전 게시물
   * @param articleDTO 변경 내용
   * @return 변경 후 게시물
   */
  public static Article create(Article article, ArticleDTO articleDTO) {
    return new Article(
        article.getId(),
        article.getAuthor(),
        articleDTO.getTitle(),
        articleDTO.getContent(),
        article.getReadCount(),
        article.getIsDeleted(),
        article.getComments(),
        article.getCreateAt(),
        article.getModifiedAt()
    );
  }


  public static Article create(
      Long id, User author, String title,
      String content, Long readCount, Delete isDeleted,
      List<Comment> comments, Timestamp createAt, Timestamp modifiedAt
  ) {
    return new Article(
        id,
        author,
        title,
        content,
        readCount,
        isDeleted,
        comments,
        createAt,
        modifiedAt
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
