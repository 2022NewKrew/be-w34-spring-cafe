package com.kakao.cafe.controller.viewdto.response;

import com.kakao.cafe.article.service.AllArticlesResponseDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleListResponse extends HashMap<String, Object> {

    public ArticleListResponse(AllArticlesResponseDTO dto) {
        ArrayList<HashMap<String, Object>> articles = new ArrayList<>();
        int index = dto.getArticles().size();

        for (AllArticlesResponseDTO.OneArticleDataDTO article : dto.getArticles()) {
            HashMap<String, Object> oneArticle = new HashMap<>();
            oneArticle.put("title", article.getTitle());
            oneArticle.put("author", article.getAuthorStringId());
            oneArticle.put("num", article.getHits());
            oneArticle.put("time", article.getWriteTime());
            oneArticle.put("articleid", article.getArticleId());
            oneArticle.put("authorid", article.getAuthorId());
            articles.add(oneArticle);
        }

        this.put("articles", articles);
    }
}
