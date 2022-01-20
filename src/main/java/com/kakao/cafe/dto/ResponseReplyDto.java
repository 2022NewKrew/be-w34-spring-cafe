package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ResponseReplyDto {
    private Long id;
//    private Long authorId;
    private String author;
    private Long articleId;
    private String content;
    private Date createdAt;

    public String getCreatedAt() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return format.format(createdAt);
    }
}
