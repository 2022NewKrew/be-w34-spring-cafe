package com.kakao.cafe.qna.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private final List<Article> articleLst;

    public ArticleRepository() {
        articleLst = new ArrayList<>();
    }

    public void submitArticle(String writer, String title, String content) {
        articleLst.add(new Article(articleLst.size() + 1, writer, title, content));
    }
}
