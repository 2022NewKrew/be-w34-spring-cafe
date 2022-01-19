package com.kakao.cafe.dto.article;

import javax.validation.constraints.NotBlank;

public class ArticleRegisterRequestDto {

    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 제목")
    private final String title;
    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 내용")
    private final String content;

    public ArticleRegisterRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
