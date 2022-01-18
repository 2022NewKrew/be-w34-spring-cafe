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
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof UserId)) {
            return false;
        }

        UserId other = (UserId) obj;
        return userId.equals(other.getValue());
    }

    @Override
    public String toString() {
        return userId;
    }
}
