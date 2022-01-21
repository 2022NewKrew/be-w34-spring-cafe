package com.kakao.cafe.qna.article;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by melodist
 * Date: 2022-01-21 021
 * Time: 오후 7:23
 */
@Data
@AllArgsConstructor
public class ArticleEditDto {

    private Integer id;
    private String title;
    private String contents;
}
