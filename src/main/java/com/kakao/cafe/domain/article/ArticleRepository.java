package com.kakao.cafe.domain.article;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Repository
public class ArticleRepository {

    private final List<Article> articleList = new ArrayList<>();
    private static Integer articleSequence = 0;

    public void save(Article article){
        article.setId(articleSequence++);
        articleList.add(article);
    }

    public List<Article> getArticleList() {
        return articleList;
    }

}
