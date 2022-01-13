package com.kakao.cafe.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;

@Getter
public class ArticleBoard {

  public static int DEFAULT_PAGE_INDEX_SIZE = 5;
  public static int DEFAULT_PAGE_SIZE = 10;
  public static int MINIMUM_PAGE_SIZE = 1;
  public static int FIRST_PAGE_INDEX = 1;
  private final int totalArticleSize;
  private ArticlePage articlePage;
  private final int currentPageIndex;
  private final int articlesPerPage;
  private final int pageIndexSize;

  private ArticleBoard(
      int totalArticleSize, ArticlePage articlePage,
      int currentPageIndex, int articlesPerPage, int pageIndexSize
  ) {
    this.totalArticleSize = totalArticleSize;
    this.articlePage = articlePage;
    this.currentPageIndex = currentPageIndex;
    this.articlesPerPage = articlesPerPage;
    this.pageIndexSize = pageIndexSize;
    checkValidation();
  }

  public static ArticleBoard createEmpty(
      int totalArticleSize, int currentPageIndex, int articlesPerPage
  ) {
    return new ArticleBoard(
        totalArticleSize, null,
        currentPageIndex, articlesPerPage, DEFAULT_PAGE_INDEX_SIZE
    );
  }

  public static ArticleBoard createEmpty(
      int totalArticleSize, int currentPageIndex
  ) {
    return new ArticleBoard(totalArticleSize, null,
        currentPageIndex, DEFAULT_PAGE_SIZE, DEFAULT_PAGE_INDEX_SIZE
    );
  }

  private void checkValidation() {

    if(currentPageIndex < FIRST_PAGE_INDEX ||
        articlesPerPage < MINIMUM_PAGE_SIZE) {
      // TODO: need customizing
      throw new RuntimeException();
    }

    if(currentPageIndex > getLastPageIndex()) {
      // TODO: need customizing
      throw new RuntimeException();
    }

  }

  public List<Pagination> getPagination() {
    return IntStream.rangeClosed(getMinIndex(), getMaxIndex())
        .mapToObj(index -> new Pagination(index, currentPageIndex == index))
        .collect(Collectors.toList());
  }

  public int getMinIndex() {
    return ((currentPageIndex - 1) / pageIndexSize) * pageIndexSize + 1;
  }

  public int getMaxIndex() {
    int maxIndex = (((currentPageIndex - 1) / pageIndexSize) + 1) * pageIndexSize;
    return Math.min(maxIndex, getLastPageIndex());
  }

  public int getLastPageIndex() {
    if(totalArticleSize == 0) {
      return FIRST_PAGE_INDEX;
    }

    if(totalArticleSize % articlesPerPage == 0) {
      return totalArticleSize / articlesPerPage;
    }

    return totalArticleSize / articlesPerPage + 1;
  }

  public boolean isNextOn() {
    return getMaxIndex() != getLastPageIndex();
  }

  public boolean isPreviousOn() {
    return getMinIndex() != FIRST_PAGE_INDEX;
  }

  public int getPreviousIndex() {
    return Math.max(getMinIndex() - 1, FIRST_PAGE_INDEX);
  }

  public int getNextIndex() {
    return Math.min(getMaxIndex() + 1, getLastPageIndex());
  }

  public int offset() {
    return (currentPageIndex - 1) * articlesPerPage;
  }

  public void setArticlePage(ArticlePage articlePage) {
    this.articlePage = articlePage;
  }

  @Override
  public String toString() {
    return "ArticleBoard{" +
        "totalArticleSize=" + totalArticleSize +
        ", articlePage=" + articlePage +
        ", currentPageIndex=" + currentPageIndex +
        ", articlesPerPage=" + articlesPerPage +
        '}';
  }

  @Getter
  public static class Pagination {

    private int index;
    private boolean isCurrent;

    private Pagination(int index, boolean isCurrent) {
      this.index = index;
      this.isCurrent = isCurrent;
    }

    public static Pagination of(int index, boolean isCurrent) {
      return new Pagination(index, isCurrent);
    }

    @Override
    public String toString() {
      return "Pagination{" +
          "index=" + index +
          ", isCurrent=" + isCurrent +
          '}';
    }

  }

}
