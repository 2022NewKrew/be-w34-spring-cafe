package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Article {

    private Integer id;
    private String writer;
    private String title;
    private String contents;
    private Integer userPk;

}
