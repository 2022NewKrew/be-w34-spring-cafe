package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;

import javax.validation.constraints.NotBlank;

public class ArticleRegistrationDto {
    @NotBlank(message = "게시글 제목이 null 이거나 한개의 띄어쓰기만 있습니다")
    private final String title;
    @NotBlank(message = "게시글 내용이 null 이거나 띄어쓰기만 있습니다")
    private final String content;

    public ArticleRegistrationDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(0, title, content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
