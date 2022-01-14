package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.TimeGenerator;
import com.kakao.cafe.service.article.ArticleService;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Article {
    String id;
    int number;
    String title;
    String content;
    String date;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = TimeGenerator.todayDate();
        this.id = ArticleService.getInstance().getArticleId();
    }

    public boolean hasId(String id) {
        return this.id.equals(id);
    }
}
