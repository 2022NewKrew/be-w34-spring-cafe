package com.kakao.cafe.web;

import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Question {
    private int id;
    private String writerId;
    private String title;
    private String contents;
    private LocalDateTime time;
    private String writerName;
}
