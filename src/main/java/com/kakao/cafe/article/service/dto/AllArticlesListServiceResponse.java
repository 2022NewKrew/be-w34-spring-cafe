package com.kakao.cafe.article.service.dto;

import com.kakao.cafe.article.domain.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Getter
public class AllArticlesListServiceResponse {

    private ArrayList<OneArticleData> articles;

    public AllArticlesListServiceResponse(ArrayList<Article> articles, ArrayList<String> authorStringIds) {
        this.articles = new ArrayList<>();
        Article article;
        for (int i = 0; i < articles.size(); i++) {
            article = articles.get(i);
            this.articles.add(OneArticleData.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .authorStringId(authorStringIds.get(i))
                    .hits(article.getHits())
                    .writeTime(article.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build());
        }

    }

    @Getter
    public static class OneArticleData {
        private Long id;
        private String title;
        private String authorStringId;
        private Integer hits;
        private String writeTime;

        @Builder
        private OneArticleData(Long id, String title, String authorStringId, Integer hits, String writeTime) {
            this.id = id;
            this.title = title;
            this.authorStringId = authorStringId;
            this.hits = hits;
            this.writeTime = writeTime;
        }
    }
}
