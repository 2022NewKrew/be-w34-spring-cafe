package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {
    private String writer;
    private String title;
    private String content;

    private QuestionDto() {}

    public static QuestionDto from(Article article) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setTitle(article.getTitle());
        questionDto.setWriter(article.getWriter());
        questionDto.setContent(article.getContent());
        return questionDto;
    }
}
