package com.kakao.cafe.domain;

import com.kakao.cafe.utility.ArticleException;
import com.kakao.cafe.utility.ErrorCode;

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
}
