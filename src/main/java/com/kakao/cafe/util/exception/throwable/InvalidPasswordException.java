package com.kakao.cafe.util.exception.throwable;

public class InvalidPasswordException extends CustomThrowableException {
    public InvalidPasswordException(String password) {
        super(String.format("패스워드가 틀립니다! : %s", password));
    }

    @Override
    public String getDetail() {
        return printStackTrace(this);
    }


}
