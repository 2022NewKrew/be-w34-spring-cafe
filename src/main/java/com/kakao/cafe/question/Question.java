package com.kakao.cafe.question;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Question {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createTime;

    public void updateTime() {
        createTime = LocalDateTime.now();
    }
}
