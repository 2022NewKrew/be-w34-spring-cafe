package com.kakao.cafe.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    USER_ALREADY_EXIST("U001", "이미 가입한 유저입니다."),
    USER_NOT_EXIST("U002", "일치하는 유저가 없습니다.");

    private final String code;
    private final String message;
}
