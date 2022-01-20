package com.kakao.cafe.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class ArticleSaveDto {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    @Setter
    private String userId;
}
