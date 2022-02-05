package com.kakao.cafe.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_NULL_VALUE(BAD_REQUEST, "NULL 값을 입력할 수 없습니다."),

    INVALID_USER_ID(BAD_REQUEST, "잘못된 형식의 아이디 입니다."),
    INVALID_USER_NAME(BAD_REQUEST, "잘못된 형식의 이름 입니다."),
    INVALID_USER_PASSWORD(BAD_REQUEST, "잘못된 형식의 패스워드 입니다."),
    INVALID_USER_EMAIL(BAD_REQUEST, "잘못된 형식의 이메일 입니다."),

    INVALID_ARTICLE_TITLE(BAD_REQUEST, "잘못된 형식의 제목 입니다."),
    INVALID_ARTICLE_CONTENTS(BAD_REQUEST, "잘못된 형식의 내용 입니다."),

    DUPLICATE_USER_ID(BAD_REQUEST, "존재하는 아이디 입니다."),
    DUPLICATE_ARTICLE_ID(BAD_REQUEST, "존재하는 게시글 입니다."),

    USER_NOT_FOUND(NOT_FOUND, "유저를 찾을 수 없습니다."),
    ARTICLE_NOT_FOUND(NOT_FOUND, "존재하지 않는 게시글 입니다."),

    CANNOT_CHANGE_USER_ID(BAD_REQUEST, "아이디를 변경할 수 없습니다."),
    WRONG_USER_PASSWORD(BAD_REQUEST, "잘못된 패스워드 입니다."),
    FAILED_LOGIN(BAD_REQUEST, "로그인에 실패했습니다."),

    FORBIDDEN_USER(FORBIDDEN, "접근 권한이 없습니다."),
    ACCESS_DENIED_USER(FORBIDDEN, "접근 권한이 없습니다.");


    private final HttpStatus httpStatus;
    private final String errorMessage;
}
