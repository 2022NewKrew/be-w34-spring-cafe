package com.kakao.cafe.web;

import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reply {
    private int id;
    private int questionId;
    private String writerId;
    private String contents;
    private LocalDateTime time;
    private String writerName;
}
