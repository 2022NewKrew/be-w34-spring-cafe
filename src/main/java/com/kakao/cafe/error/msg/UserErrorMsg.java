package com.kakao.cafe.error.msg;

import lombok.Getter;

@Getter
public enum UserErrorMsg {

    USER_NOT_FOUNDED("U001", "User not founded"),
    USER_ALREADY_EXIST("U002", "User already exist"),
    USER_EMAIL_DUPLICATED("U003", "User email duplication founded"),
    USER_NICKNAME_DUPLICATED("U004", "User nickname duplication founded");

    private String code;
    private String description;

    UserErrorMsg(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
