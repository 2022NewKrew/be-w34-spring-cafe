package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository {

    void createArticle(Article article);

    Integer articlesSize();

    List<Article> readArticleList();

    boolean isIdUsed(Integer aid);

    Article readArticle(Integer aid);
}
