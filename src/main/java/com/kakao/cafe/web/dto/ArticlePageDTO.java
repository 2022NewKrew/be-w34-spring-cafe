package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticlePage;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticlePageDTO implements Iterable<ArticleDTO> {

  private List<ArticleDTO> articles;

  public ArticlePageDTO(ArticlePage articlePage) {
    this.articles = fromDomain(articlePage.getArticles());
  }

  private List<ArticleDTO> fromDomain(List<Article> articles) {
    return articles.stream()
        .map(ArticleDTO::new)
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "ArticlePageDTO{" +
        "articles=" + articles +
        '}';
  }

  @Override
  public Iterator<ArticleDTO> iterator() {
    return articles.iterator();
  }

  @Override
  public void forEach(Consumer<? super ArticleDTO> action) {
    articles.forEach(action);
  }

}
