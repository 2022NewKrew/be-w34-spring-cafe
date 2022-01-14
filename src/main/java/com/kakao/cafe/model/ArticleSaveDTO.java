package com.kakao.cafe.model;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
public class ArticleSaveDTO {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    public ArticleSaveDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
