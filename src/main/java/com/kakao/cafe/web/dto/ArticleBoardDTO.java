package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.ArticleBoard;
import com.kakao.cafe.domain.ArticleBoard.Pagination;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ArticleBoardDTO {

  private int totalArticleSize;
  private ArticlePageDTO articlePage;
  private int currentPageIndex;
  private List<Pagination> pagination;
  private boolean previousOn;
  private boolean nextOn;
  private int previousIndex;
  private int nextIndex;

  public ArticleBoardDTO(ArticleBoard articleBoard) {
    this.totalArticleSize = articleBoard.getTotalArticleSize();
    this.articlePage = new ArticlePageDTO(articleBoard.getArticlePage());
    this.currentPageIndex = articleBoard.getCurrentPageIndex();
    this.pagination = articleBoard.getPagination();
    this.nextOn = articleBoard.isNextOn();
    this.previousOn = articleBoard.isPreviousOn();
    this.previousIndex = articleBoard.getPreviousIndex();
    this.nextIndex = articleBoard.getNextIndex();
  }

  @Override
  public String toString() {
    return "ArticleBoardDTO{" +
        "totalArticleSize=" + totalArticleSize +
        ", articlePage=" + articlePage +
        ", currentPageIndex=" + currentPageIndex +
        ", pagination=" + pagination +
        '}';
  }

}
