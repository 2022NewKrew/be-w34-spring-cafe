package com.kakao.cafe.model;

/**
 * author    : brody.moon
 * version   : 1.0
 * UserAccount 클래스에서 로직을 제거하고 데이터 전송을 위해 따로 만든 클래스입니다.
 */
public class UserAccountDTO {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private int index;

    public UserAccountDTO(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "UserAccountDTO{" +
                "userID='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
