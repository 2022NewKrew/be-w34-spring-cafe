package com.kakao.cafe.model.article;

import com.kakao.cafe.utility.NullChecker;
import java.util.Objects;

public class Title {

    private static final int ALLOWED_LENGTH_TITLE = 16;

    private final String value;

    public Title(String title) {
        checkTitle(title);

        this.value = title;
    }

    private void checkTitle(String title) {
        NullChecker.checkNotNull(title);

        if (title.length() > ALLOWED_LENGTH_TITLE) {
            throw new IllegalArgumentException(
                    String.format("제목의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_TITLE));
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
        Title title = (Title) o;
        return Objects.equals(value, title.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
