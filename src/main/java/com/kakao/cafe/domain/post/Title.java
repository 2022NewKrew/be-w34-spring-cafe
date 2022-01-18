package com.kakao.cafe.domain.post;

public class Title {
    private final String title;

    public Title(String title) {
        if (title == null)
            throw new IllegalArgumentException("Title : title == null");
        title = title.trim();
        if (title.length() == 0)
            throw new IllegalArgumentException("Title : title.length() == 0");
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
