package com.kakao.cafe.domain.article;

import java.util.Objects;

public class ViewCount {

    private int value;

    public ViewCount(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void increase() {
        this.value += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ViewCount that = (ViewCount) o;
        return value == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
