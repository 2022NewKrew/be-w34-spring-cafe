package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Comment {
    private long id;
    private long articleId;
    private String writer;
    private String contents;
    private String time;

    public Comment(long articleId, String writer, String contents){
        this.articleId = articleId;
        this.writer = writer;
        this.contents = contents;
        this.time = getCreatedTime();
    }

    private String getCreatedTime() {
        LocalDateTime nowDateTime = LocalDateTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return nowDateTime.format(dateFormatter);
    }
}
