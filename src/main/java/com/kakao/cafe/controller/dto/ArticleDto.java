package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ArticleDto {

    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime date;
    private List<Reply> replies = new ArrayList<>();

    private ArticleDto() {
    }

    private ArticleDto(int id, String title, String content, String writer, LocalDateTime date, List<Reply> replies) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = date;
        this.replies = replies;
    }

    public Article toEntity() {
        return new Article(title, content, writer);
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent(),
                article.getWriter(), article.getDate(), article.getReplies());
    }

    @Override
    public String toString() {
        return String.format(
                "ArticleDto{id=%d, title=%s, content=%s, writer=%s, date=%s}",
                this.id,
                this.title,
                this.content,
                this.writer,
                this.date
        );
    }
}
