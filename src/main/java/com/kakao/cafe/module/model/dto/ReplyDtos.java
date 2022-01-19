package com.kakao.cafe.module.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ReplyDtos {

    @Getter
    @AllArgsConstructor
    public static class ReplyPostDto {

        private final String comment;
    }
}
