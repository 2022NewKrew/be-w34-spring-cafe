package com.kakao.cafe.dao;

import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ArticleDAO {

    List<Article> articleList = new ArrayList<>();

    default List<Article> findAllArticle() {
        return articleList;
    }

    default void writeArticle(Article article) {
        articleList.add(article);
    }
}
