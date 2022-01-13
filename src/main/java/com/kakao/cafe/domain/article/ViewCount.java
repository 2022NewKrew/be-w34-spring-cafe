package com.kakao.cafe.domain.article;

public class ViewCount {

    private int value;

    public ViewCount(int value) {
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
