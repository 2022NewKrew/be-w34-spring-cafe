package com.kakao.cafe.error.exception;

import static com.kakao.cafe.Constant.MESSAGE_REPLY_NOT_FOUND;

public class ReplyNotFoundException extends RuntimeException{
    public ReplyNotFoundException() {
        super(MESSAGE_REPLY_NOT_FOUND);
    }
}
