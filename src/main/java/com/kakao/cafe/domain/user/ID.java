package com.kakao.cafe.domain.user;

public class ID {
    private final String id;

    public ID(String id) {
        if (id == null || id.trim().length() == 0)
            throw new IllegalArgumentException("잘못된 입력입니다!");
        this.id = id.trim();
    }

    public boolean is(String id) {
        return this.id.equals(id);
    }

    public String getId() {
        return id;
    }
}
