package com.kakao.cafe.model;

import java.util.Objects;

public class ArticleSaveDTO {
    private final String title;
    private final String content;

    public ArticleSaveDTO(String title, String content) {
        validateTitle(title);
        validateContent(content);

        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


    private void validateTitle(String title) {
        if (Objects.isNull(title) || title.length() <= 0) throw new IllegalArgumentException();
    }

    private void validateContent(String content) {
        if (Objects.isNull(content) || content.length() <= 0) throw new IllegalArgumentException();
    }
}
