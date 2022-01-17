package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Article {

    private final Title title;
    private final Text text;
    private Member author;
    private final Time time;
    private Comments comments;
    private Long articleId;

    public Article(Title title, Text text, Member author, Time time, Long articleId) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.time = time;
        this.articleId = articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title) && Objects.equals(text, article.text) && Objects.equals(author, article.author) && Objects.equals(time, article.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, author, time, comments, articleId);
    }
}
