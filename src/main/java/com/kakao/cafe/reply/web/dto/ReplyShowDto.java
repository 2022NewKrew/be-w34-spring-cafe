package com.kakao.cafe.reply.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReplyShowDto {

    private int id;
    private String writer;
    private String contents;
}
