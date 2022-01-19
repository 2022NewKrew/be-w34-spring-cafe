package com.kakao.cafe.reply.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReplyDto {
    Long id;
    String writer;
    String comment;
    LocalDateTime createTime;
    boolean thisIsMine;
}
