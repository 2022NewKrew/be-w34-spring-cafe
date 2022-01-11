package com.kakao.cafe.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArticleDTO {
    private String title;
    private String content;
    private String author;
    private LocalDate createdDate;
    private Integer views;
    private Integer postIdx;

    public ArticleDTO(String title, String content) {
        this.title = title;
        this.content = content;
        this.author = "익명";
        this.createdDate = LocalDate.now();
        this.views = 0;
    }
}
