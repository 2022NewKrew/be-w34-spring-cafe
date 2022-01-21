package com.kakao.cafe.interfaces.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDto {
    private String writer;
    private String body;

    @Builder
    public ReplyDto(String writer, String body) {
        this.writer = writer;
        this.body = body;
    }
}
