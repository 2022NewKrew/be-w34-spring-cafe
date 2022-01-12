package com.kakao.cafe.domain.article;

public class ViewCount {

    private int value;

    public ViewCount() {
        this.value = 0;
    }

    public ViewCount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void increase() {
        this.value += 1;
    }
}
