package com.kakao.cafe.model.article;

import com.kakao.cafe.utility.NullChecker;

import java.util.Objects;

public class Contents {
    private static final int ALLOWED_LENGTH_CONTENTS = 100;

    private final String value;

    public Contents(String contents) {
        checkContents(contents);

        this.value = contents;
    }

    private void checkContents(String contents) {
        NullChecker.checkNotNull(contents);

        if (contents.length() > ALLOWED_LENGTH_CONTENTS) {
            throw new IllegalArgumentException(String.format("본문의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_CONTENTS));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contents contents = (Contents) o;
        return Objects.equals(value, contents.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
