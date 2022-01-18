package com.kakao.cafe.service.dto;

import com.kakao.cafe.controller.dto.request.ArticleUpdateRequestDto;
import lombok.Getter;

@Getter
public class ArticleUpdateDto {

    private final Long id;
    private final String title;
    private final String contents;

    public ArticleUpdateDto(Long id, ArticleUpdateRequestDto articleUpdateRequestDto) {
        this.id = id;
        title = articleUpdateRequestDto.getTitle();
        contents = articleUpdateRequestDto.getContents();
    }
}
