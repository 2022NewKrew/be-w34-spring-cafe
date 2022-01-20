package com.kakao.cafe.qna.article;

import com.kakao.cafe.qna.BaseEntity;
import com.kakao.cafe.qna.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Getter
@AllArgsConstructor
public class Article extends BaseEntity {

    private String title;
    private Integer commentsCount;
    private List<Comment> comments;

    public Article(String writer, String title, String contents) {
        super(writer, contents);
        this.title = title;
        this.commentsCount = 0;
        this.comments = new ArrayList<>();
    }

    public Article(int id,
                   String writer,
                   String title,
                   String contents,
                   Integer commentsCount,
                   Boolean isDeleted,
                   LocalDateTime created_date,
                   LocalDateTime modified_date) {
        super(id, writer, contents, isDeleted, created_date, modified_date);
        this.commentsCount = commentsCount;
        this.title = title;
        this.comments = new ArrayList<>();
    }

    public void updateContents(String title, String contents) {
        this.title = title;
        super.updateContents(contents);
    }

    public void deleteArticle() {
        deleteEntity();
    }

    public void addComments(Comment comment) {
        comments.add(comment);
    }
}
