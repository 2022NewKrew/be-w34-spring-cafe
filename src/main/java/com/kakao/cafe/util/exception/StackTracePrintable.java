package com.kakao.cafe.util.exception;

public interface StackTracePrintable {

    default String printStackTrace(Exception exception) {
        StringBuilder sb = new StringBuilder();
        sb.append(exception.getMessage() + "\n");
        for (StackTraceElement elem : exception.getStackTrace())
            sb.append(elem.toString() + "\n");
        return sb.toString();
    }

    public String getDetail();
}
