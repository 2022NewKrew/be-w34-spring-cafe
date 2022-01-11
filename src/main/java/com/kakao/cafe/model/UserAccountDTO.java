package com.kakao.cafe.model;

/**
 * author    : brody.moon
 * version   : 1.0
 * UserAccount 클래스에서 로직을 제거하고 데이터 전송을 위해 따로 만든 클래스입니다.
 */
public class UserAccountDTO {
    private final String userID;
    private final String password;
    private final String name;
    private final String email;
    private int index;

    public UserAccountDTO(String userID, String password, String name, String email) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUserID() {
        return userID;
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
                "userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
