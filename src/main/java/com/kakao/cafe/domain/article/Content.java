package com.kakao.cafe.domain.article;

public class Content {
    private final String content;

    public Content(String content) {
        this.content = content;
    }

    public String getValue() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Content)) {
            return false;
        }

        return content.equals(((Content) obj).getValue());
    }
}
