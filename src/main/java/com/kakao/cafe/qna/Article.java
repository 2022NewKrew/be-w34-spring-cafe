package com.kakao.cafe.qna;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Getter
@NoArgsConstructor
public class Article {

    public Article(String writer, String title, String contents, Integer replyCount, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.replyCount = replyCount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    private int id;

    private String writer;
    private String title;
    private String contents;
    private Integer replyCount;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public void setId(int id) {
        this.id = id;
    }
}
