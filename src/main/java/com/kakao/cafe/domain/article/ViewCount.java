package com.kakao.cafe.domain.article;

public class ViewCount {

    private int value;

    public ViewCount(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        this.value = value;
    }

    public ViewCount() {
        this(0);
    }

    public int getValue() {
        return value;
    }

    public void increase() {
        this.value += 1;
    }
}
