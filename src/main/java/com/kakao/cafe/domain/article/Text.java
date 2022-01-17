package com.kakao.cafe.domain.article;

import java.util.Objects;

public class Text {

    private final String text;

    public Text(String text) {
        this.text = text.replaceAll("[<>/]", "");
    }


    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text.replace("\r\n", "<br>");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text1 = (Text) o;
        return Objects.equals(text, text1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
