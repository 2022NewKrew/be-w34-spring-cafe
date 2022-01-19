package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

//@RequiredArgsConstructor
@Getter
@AllArgsConstructor
public class Comment {

    private final Article article;
    private final Time time;
    private final Text text;
    private final Member author;
    private Long commentId;

    protected Comment(Article article, Time time, Text text, Member author) {
        this.article = article;
        this.time = time;
        this.text = text;
        this.author = author;
    }

    public static Comment createComment(Article article, Time time, Text text, Member author) {
        Comment comment = new Comment(article, time, text, author);
        article.getComments().add(comment);
        return comment;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) && Objects.equals(article, comment.article) && Objects.equals(time, comment.time) && Objects.equals(text, comment.text) && Objects.equals(author, comment.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, article, time, text, author);
    }
}
