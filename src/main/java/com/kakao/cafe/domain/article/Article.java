package com.kakao.cafe.domain.article;

import com.kakao.cafe.utils.TimeGenerator;
import com.kakao.cafe.service.article.ArticleService;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Article {
    private Long id;
    private final String title;
    private final String content;
    private final String date;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = TimeGenerator.todayDate();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId(Long id) {
        return this.id.equals(id);
    }
}
