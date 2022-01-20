package com.kakao.cafe.infra.exception;

public class DeleteRuleException extends CustomRuntimeException{

    public DeleteRuleException(String msg) {
        super(msg);
        name = "DeleteRuleException";
        errorCode = ErrorCode.BAD_REQUEST;
    }
}
