package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ArticlePage {

  private final List<Article> articles;

  private ArticlePage(List<Article> articles) {
    int size = ArticleBoard.DEFAULT_PAGE_SIZE;
    this.articles = articles != null ? articles : new ArrayList<>(size);
  }

  public static ArticlePage createEmpty() {
    return new ArticlePage(null);
  }

  public static ArticlePage of(List<Article> articles) {
    return new ArticlePage(articles);
  }

  @Override
  public String toString() {
    return "ArticlePage{" +
        "articles=" + articles +
        '}';
  }
}
