package com.kakao.cafe.model;

/**
 * author    : brody.moon
 * version   : 1.0
 * User 계정 정보 클래스입니다.
 */
public class UserAccount {
    private final String userID;
    private final String password;
    private final String name;
    private final String email;

    /**
     * 중복 등록을 방지하기 위해 private 생성자로 선언하였습니다.
     * @param userID    userID
     * @param password  password
     * @param name      name
     * @param email     email
     */
    private UserAccount(String userID, String password, String name, String email) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    /**
     * 계정 생성을 위한 static 메서드입니다.
     * @param userID    userID
     * @param password  password
     * @param name      name
     * @param email     email
     * @return          UserAccount 객체
     */
    public static UserAccount createUserAccount(String userID, String password, String name, String email){
        if(!DataStorage.isExistUserAccount(userID))
            return new UserAccount(userID, password, name, email);

        return null;
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
