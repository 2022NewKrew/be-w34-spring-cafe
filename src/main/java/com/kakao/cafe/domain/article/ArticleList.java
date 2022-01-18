package com.kakao.cafe.domain.article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleList {

    private final List<Article> articleList;

    public ArticleList(List<Article> articleList) {
        List<Article> tempList = new ArrayList<>(articleList);
        this.articleList = Collections.unmodifiableList(tempList);
    }

    public int size() {
        return articleList.size();
    }

    public List<Article> getCopiedArticleList() {
        return new ArrayList<>(articleList);
    }

}
