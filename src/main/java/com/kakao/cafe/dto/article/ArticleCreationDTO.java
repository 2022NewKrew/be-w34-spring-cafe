package com.kakao.cafe.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreationDTO {
    private long userId;
    private String title;
    private String body;
}
