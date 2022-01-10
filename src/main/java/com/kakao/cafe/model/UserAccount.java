package com.kakao.cafe.model;

public class UserAccount {
    private final String userID;
    private final String passward;
    private final String name;
    private final String email;

    private UserAccount(String userID, String passward, String name, String email) {
        this.userID = userID;
        this.passward = passward;
        this.name = name;
        this.email = email;
    }

    public static UserAccount createUserAccount(String userID, String passward, String name, String email){
        if(!DataStorage.isExistUserAccount(userID))
            return new UserAccount(userID, passward, name, email);

        return null;
    }
}
