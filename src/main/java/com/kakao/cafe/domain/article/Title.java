package com.kakao.cafe.domain.article;

public class Title {
    private final String title;

    public Title(String title) {
        this.title = title;
    }

    public String getValue() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Title)) {
            return false;
        }

        return title.equals(((Title) obj).getValue());
    }
}
