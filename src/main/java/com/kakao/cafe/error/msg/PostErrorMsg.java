package com.kakao.cafe.error.msg;

import lombok.Getter;

@Getter
public enum PostErrorMsg {

    POST_NOT_FOUNDED("P001", "Post not founded"),
    POST_NOT_MINE("P002", "Post is not mine")
    ;

    private String code;
    private String description;

    PostErrorMsg(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
