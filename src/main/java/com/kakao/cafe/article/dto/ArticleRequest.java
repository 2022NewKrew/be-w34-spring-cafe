package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    private String author;
    private String title;
    private String contents;
}
