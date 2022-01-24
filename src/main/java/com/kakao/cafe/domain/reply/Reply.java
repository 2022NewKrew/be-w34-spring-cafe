package com.kakao.cafe.domain.reply;

import lombok.Data;


@Data
public class Reply {
    private Long id;
    private String content;
    private String date;
    private String writer;
    private Long writerId;
    private Long articleId;
    private Boolean deleted = false;
}
