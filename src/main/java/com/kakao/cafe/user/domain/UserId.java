package com.kakao.cafe.user.domain;

public class UserId {

    private final long id;

    public UserId(String id) {
        long longFormatId = Long.parseLong(id);
        validateUserId(longFormatId);
        this.id = longFormatId;
    }

    public UserId(long id) {
        validateUserId(id);
        this.id = id;
    }

    private void validateUserId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid User ID format");
        }
    }

    public long getId() {
        return id;
    }
}
