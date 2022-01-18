package com.kakao.cafe.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {

    private int id;
    private String writer;
    private String title;
    private String contents;
    private String date;
    private String userId;
}
