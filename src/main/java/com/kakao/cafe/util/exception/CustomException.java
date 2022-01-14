package com.kakao.cafe.util.exception;

public abstract class CustomException extends RuntimeException {
    private Exception exception;

    public CustomException(Exception exception, String message) {
        super(message);
        this.exception = exception;
    }

    public String getDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append(exception.getMessage() + "\n");
        for (StackTraceElement elem : exception.getStackTrace())
            sb.append(elem.toString() + "\n");
        return sb.toString();
    }
}
