package com.kakao.cafe.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class ReplyWriteDto {

    @NotBlank
    private final String content;

    @Setter
    private int articleId;

    @Setter
    private String userId;
}
