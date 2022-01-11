package com.kakao.cafe.article;

import org.springframework.stereotype.Repository;

@Repository
public class CustomArticleRepository implements ArticleRepository {

    public void update(Article article) {
        System.out.println("TODO");
    }

}
