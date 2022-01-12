package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class ArticleDto {
    private String author;
    private String title;
    private String content;

    public String toString(){
        return String.format("{author = %s," +
                "title = %s," +
                "content = %s}", author, title, content);
    }
}
