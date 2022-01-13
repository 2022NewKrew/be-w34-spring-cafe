package com.kakao.cafe.domain;

import com.kakao.cafe.utility.ArticleException;
import com.kakao.cafe.utility.ErrorCode;
import lombok.Getter;

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
}
