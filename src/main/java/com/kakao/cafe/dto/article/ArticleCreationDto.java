package com.kakao.cafe.dto.article;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ArticleCreationDto {
    private long userId;
    @NotEmpty(message="제목을 입력해주세요.")
    private String title;
    @NotEmpty(message="내용을 입력해주세요.")
    private String body;
}
