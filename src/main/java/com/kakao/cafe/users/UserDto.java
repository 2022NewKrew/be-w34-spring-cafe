package com.kakao.cafe.users;

public class UserDto {

    private final long userSeq;

    private final String userId;

    private final String name;

    private final String email;

    public UserDto(User user) {
        this.userSeq = user.getSeq();
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public long getUserSeq() {
        return userSeq;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
