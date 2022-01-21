package com.kakao.cafe.reply.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Reply {

    int id;
    private String writer;
    private int article;
    private String contents;
}
