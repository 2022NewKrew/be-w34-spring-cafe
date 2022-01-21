package com.kakao.cafe.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyRegisterRequestDto {

    private final String writerId;
    private final String comment;
}
