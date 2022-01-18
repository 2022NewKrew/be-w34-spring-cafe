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
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Content)) {
            return false;
        }

        Content other = (Content) obj;
        return content.equals(other.getValue());
    }

    @Override
    public String toString() {
        return content;
    }
}
