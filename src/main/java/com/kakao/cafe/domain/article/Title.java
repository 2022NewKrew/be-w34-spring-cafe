package com.kakao.cafe.domain.article;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Title {

    private final String title;

    public Title(String title) {
        this.title = title.replaceAll("[<>/]", "");
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
