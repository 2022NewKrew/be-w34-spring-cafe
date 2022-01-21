package com.kakao.cafe.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    USER_ALREADY_EXIST("U001", "이미 가입한 유저입니다."),
    USER_NOT_EXIST("U002", "일치하는 유저가 없습니다."),
    USER_INFO_MISMATCH("U003", "유저 정보가 서로 일치하지 않습니다."),
    USER_PASSWORD_INCORRECT("U004", "유저 비밀번호가 일치하지 않습니다"),

    ARTICLE_NOT_FOUND("P001", "해당 글을 찾을 수 없습니다."),
    ARTICLE_AUTHOR_MISMATCH("P002", "해당 글의 작성자가 아닙니다."),
    ARTICLE_CANT_DELETE("P003", "해당 글을 삭제할 수 없습니다."),
    COMMENT_NOT_FOUND("P004", "해당 댓글을 찾을 수 없습니다."),
    COMMENT_AUTHOR_MISMATCH("P005", "해당 댓글의 작성자가 아닙니다."),

    ID_PASSWORD_MISMATCH("A001", "ID 또는 비밀번호가 일치 하지 않습니다.");


    private final String code;
    private final String message;
}
