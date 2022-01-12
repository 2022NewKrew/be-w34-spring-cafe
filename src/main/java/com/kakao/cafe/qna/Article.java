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

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public void setId(Integer id) {
        this.id = id;
    }
}
