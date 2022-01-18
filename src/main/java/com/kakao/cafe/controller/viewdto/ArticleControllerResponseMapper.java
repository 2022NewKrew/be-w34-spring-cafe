package com.kakao.cafe.controller.viewdto;

import com.kakao.cafe.article.service.dto.AllArticlesListServiceResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleControllerResponseMapper {

    public static List<Map<String, Object>> getArticleListResponse(AllArticlesListServiceResponse dto){
        List<Map<String, Object>> articles = new ArrayList<>();
        for (AllArticlesListServiceResponse.OneArticleData article : dto.getArticles()) {
            HashMap<String, Object> oneArticle = new HashMap<>();
            oneArticle.put("title", article.getTitle());
            oneArticle.put("author", article.getAuthorStringId());
            oneArticle.put("num", article.getHits());
            oneArticle.put("time", article.getWriteTime());
            oneArticle.put("articleid", article.getId());
            articles.add(oneArticle);
        }
        return articles;
    }
}
