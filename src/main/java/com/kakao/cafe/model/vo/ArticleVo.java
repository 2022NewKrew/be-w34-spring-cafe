package com.kakao.cafe.model.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {

    private int id;
    private UserVo writer;
    private String title;
    private String contents;
}
