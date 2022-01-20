package com.kakao.cafe.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class ArticleSaveDTO {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
