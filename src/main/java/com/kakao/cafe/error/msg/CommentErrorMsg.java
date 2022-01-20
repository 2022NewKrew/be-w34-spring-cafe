package com.kakao.cafe.error.msg;

import lombok.Getter;

@Getter
public enum CommentErrorMsg {

    COMMENT_NOT_FOUNDED("C001", "Comment not founded"),
    COMMENT_NOT_MINE("C002", "Comment not mine")
    ;

    private String code;
    private String description;

    CommentErrorMsg(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
