package com.kakao.cafe.domain.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Reply {
    private Long id;
    private String content;
    private String date;
    private String writer;
    private Long writerId;
    private Long articleId;
}
