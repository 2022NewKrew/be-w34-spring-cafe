package com.kakao.cafe.domain.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleList {
    private static List<Article> articleList = new ArrayList<>();

    public static void addArticle(Article article){
        articleList.add(article);
    }

    public static List<Article> getArticleList(){
        return articleList;
    }

    public static Article getArticleByIndex(int index){
        return articleList.get(index-1);
    }
}
