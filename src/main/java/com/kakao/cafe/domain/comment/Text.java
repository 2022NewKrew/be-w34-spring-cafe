package com.kakao.cafe.domain.comment;

public class Text {

    private final String text;

    public Text(String text) {
        if (text == null)
            throw new IllegalArgumentException("Text : 입력값이 null입니다!");
        if (text.trim().length() == 0)
            throw new IllegalArgumentException("Text : 입력값이 빈 문자열입니다!");

        this.text = text;
    }

    public String getText() {
        return text;
    }
}
