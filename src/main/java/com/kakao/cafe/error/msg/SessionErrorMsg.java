package com.kakao.cafe.error.msg;

import lombok.Getter;

@Getter
public enum SessionErrorMsg {

    SESSION_ATTRIBUTES_NOT_FOUNDED_ERROR("S001", "Session attribute is not founded");

    private String code;
    private String description;

    SessionErrorMsg(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
