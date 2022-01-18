package com.kakao.cafe.infra.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401),
    BAD_REQUEST(400),
    FORBIDDEN(403);

    @Getter
    private final int value;
}
