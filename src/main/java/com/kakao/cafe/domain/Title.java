package com.kakao.cafe.domain;

import com.kakao.cafe.exception.ArticleException;
import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Title {
    private final String title;

    public Title(String title) {
        validateNull(title);
        validateEmpty(title);
        validateNewLine(title);
        this.title = title;
    }

    private void validateNull(String title) {
        if (title == null) {
            throw new ArticleException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateEmpty(String title) {
        if (title.length() == 0) {
            throw new ArticleException(ErrorCode.INVALID_ARTICLE_TITLE);
        }
    }

    private void validateNewLine(String title) {
        if (title.contains("\\r") || title.contains("\\n")) {
            throw new ArticleException(ErrorCode.INVALID_ARTICLE_TITLE);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Title title = (Title) object;
        return Objects.equals(this.title, title.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
