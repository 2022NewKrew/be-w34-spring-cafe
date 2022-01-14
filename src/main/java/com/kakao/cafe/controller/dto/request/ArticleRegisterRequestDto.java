package com.kakao.cafe.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class ArticleRegisterRequestDto {

    @NotBlank
    private final String writerId;
    @NotBlank
    private final String title;
    private final String contents;

}
