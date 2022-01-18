package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
public class ResponseArticleDto {
    private long id;
    private String author;
    private String title;
    private String content;
    private long views;
    private Date createdAt;

    public String getCreatedAt(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(createdAt);
    }
}
