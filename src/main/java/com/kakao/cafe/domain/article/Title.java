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
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Title)) {
            return false;
        }

        Title other = (Title) obj;
        return title.equals(other.getValue());
    }

    @Override
    public String toString() {
        return title;
    }
}
