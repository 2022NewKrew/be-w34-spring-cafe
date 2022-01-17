package com.kakao.cafe.domain;

import com.kakao.cafe.utils.DateUtils;

public class User {
    private long userId;
    private String email;
    private String password;
    private String nickname;
    // LocalDate
    private String createDate;

    private User() { }

    private User(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.createDate = DateUtils.getCurrentTime();
    }

    public static User from(User user) {
        return new User(user);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
