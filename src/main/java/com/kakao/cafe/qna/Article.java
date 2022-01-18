package com.kakao.cafe.qna;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Getter
@AllArgsConstructor
public class Article {

    private Integer id;

    private String writer;
    private String title;
    private String contents;
    private Integer replyCount;

    private Boolean isDeleted;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Article(String writer, String title, String contents) {
        this.id = null;

        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.replyCount = 0;

        this.isDeleted = Boolean.FALSE;

        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void updateContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.modifiedDate = LocalDateTime.now();
    }

    public void deleteArticle() {
        this.isDeleted = Boolean.TRUE;
        this.modifiedDate = LocalDateTime.now();
    }
}
