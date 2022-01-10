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

    public UserAccountDTO(UserAccount userAccount) {
        this.userID = userAccount.getUserID();
        this.password = userAccount.getPassword();
        this.name = userAccount.getName();
        this.email = userAccount.getEmail();
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
}
