package com.kakao.cafe.reply.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class ReplyCreateRequest {

    @NotNull
    @NotBlank
    private final String contents;
}
