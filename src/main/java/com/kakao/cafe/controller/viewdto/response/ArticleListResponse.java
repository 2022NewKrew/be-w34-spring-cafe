package com.kakao.cafe.controller.viewdto.response;

import com.kakao.cafe.article.service.dto.AllArticlesListServiceResponse;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleListResponse extends HashMap<String, Object> {

    public ArticleListResponse(AllArticlesListServiceResponse dto) {
        ArrayList<HashMap<String, Object>> articles = new ArrayList<>();
        int index = dto.getArticles().size();

        for (AllArticlesListServiceResponse.OneArticleData article : dto.getArticles()) {
            HashMap<String, Object> oneArticle = new HashMap<>();
            oneArticle.put("title", article.getTitle());
            oneArticle.put("author", article.getAuthorStringId());
            oneArticle.put("num", article.getHits());
            oneArticle.put("time", article.getWriteTime());
            oneArticle.put("articleid", article.getId());
            articles.add(oneArticle);
        }

        this.put("articles", articles);
    }
}
