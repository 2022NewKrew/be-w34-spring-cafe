package com.kakao.cafe.dto.article;

import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
public class ArticleWriteDto {

    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 작성자 아이디")
    private final String userName;
    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 제목")
    private final String title;
    @NotBlank(message = "필수 입력 항목이 누락되었습니다: 내용")
    private final String content;

    public ArticleWriteDto(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}
