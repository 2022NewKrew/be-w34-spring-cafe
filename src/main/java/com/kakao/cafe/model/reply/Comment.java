package com.kakao.cafe.model.reply;

import com.kakao.cafe.utility.NullChecker;
import java.util.Objects;

public class Comment {

    private static final int ALLOWED_LENGTH_COMMENT = 50;

    private final String value;

    public Comment(String value) {
        checkComment(value);

        this.value = value;
    }

    private void checkComment(String value) {
        NullChecker.checkNotNull(value);

        if (value.length() > ALLOWED_LENGTH_COMMENT) {
            throw new IllegalArgumentException(
                    String.format("댓글의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_COMMENT));
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
        Comment comment = (Comment) o;
        return Objects.equals(value, comment.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
