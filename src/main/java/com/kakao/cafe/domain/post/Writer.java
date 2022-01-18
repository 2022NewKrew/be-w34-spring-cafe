package com.kakao.cafe.domain.post;

public class Writer {
    private final String writer;

    public Writer(String writer) {
        if (writer == null || writer.trim().length() == 0)
            throw new IllegalArgumentException(String.format("Writer : writer = %s", writer));
        this.writer = writer;
    }

    public String getWriter() {
        return writer;
    }

    public boolean is(String curId) {
        return writer.equals(curId);
    }
}
