package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Getter
public class AllArticlesResponseDTO {

    private ArrayList<OneArticleDataDTO> articles;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AllArticlesResponseDTO(ArrayList<Article> articles, ArrayList<String> authorStringIds) {
        this.articles = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            this.articles.add(
                    new OneArticleDataDTO(
                            articles.get(i).getId(),
                            articles.get(i).getTitle(),
                            articles.get(i).getAuthorId(),
                            authorStringIds.get(i),
                            articles.get(i).getHits(),
                            articles.get(i).getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    ));
        }
    }

    @AllArgsConstructor
    @Getter
    public static class OneArticleDataDTO {
        public Long articleId;
        public String title;
        public Long authorId;
        public String authorStringId;
        public Integer hits;
        public String writeTime;
    }
}
