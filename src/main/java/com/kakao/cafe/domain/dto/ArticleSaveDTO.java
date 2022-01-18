package com.kakao.cafe.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@Setter
public class ArticleSaveDTO {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    private String userId;


}
