package com.kakao.cafe.domain;

import com.kakao.cafe.exception.ArticleException;
import com.kakao.cafe.util.ErrorCode;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Contents {
    private final String contents;

    public Contents(String contents) {
        validateNull(contents);
        validateEmpty(contents);
        this.contents = contents;
    }

    private void validateNull(String contents) {
        if (contents == null) {
            throw new ArticleException(ErrorCode.INVALID_NULL_VALUE);
        }
    }

    private void validateEmpty(String contents) {
        if (contents.length() == 0) {
            throw new ArticleException(ErrorCode.INVALID_ARTICLE_CONTENTS);
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
        Contents contents = (Contents) object;
        return Objects.equals(this.contents, contents.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }
}
