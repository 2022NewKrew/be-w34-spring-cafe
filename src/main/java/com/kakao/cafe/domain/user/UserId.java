package com.kakao.cafe.domain.user;

public class UserId {

    private final String userId;

    public UserId(String userId) {
        this.userId = userId;
    }

    public String getValue() {
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UserId)) {
            return false;
        }

        return userId.equals(((UserId) obj).getValue());
    }
}
