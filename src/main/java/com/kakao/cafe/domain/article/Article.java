package com.kakao.cafe.domain.article;

import com.kakao.cafe.utils.TimeGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Article {
    private Long id;
    private final String title;
    private final String content;
    private final String date;

    public static Article fromPost(String title, String content) {
        return Article.builder()
                .content(content)
                .title(title)
                .date(TimeGenerator.todayDate()).build();
    }

    @Builder
    public Article(Long id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId(Long id) {
        return this.id.equals(id);
    }
}
