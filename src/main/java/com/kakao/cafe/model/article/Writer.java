package com.kakao.cafe.model.article;

import com.kakao.cafe.utility.NullChecker;

import java.util.Objects;

public class Writer {

    private static final int ALLOWED_LENGTH_WRITER = 8;

    private final String value;

    public Writer(String writer) {
        checkWriter(writer);

        this.value = writer;
    }

    private void checkWriter(String writer) {
        NullChecker.checkNotNull(writer);

        if (writer.length() > ALLOWED_LENGTH_WRITER) {
            throw new IllegalArgumentException(
                    String.format("작성자 이름의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_WRITER));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Writer writer = (Writer) o;
        return Objects.equals(value, writer.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
