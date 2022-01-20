package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository {

    void createArticle(Article article);

    Integer articlesSize();

    List<Article> findAllArticles();

    boolean isArticleIdUsed(Integer aid);

    Article findArticleByArticleId(Integer aid);
}
