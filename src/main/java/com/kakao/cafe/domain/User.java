package com.kakao.cafe.domain;

public class User {
    private String uID;
    private String password;
    private String name;
    private String email;

    public User(String uID, String password, String name, String email) {
        this.uID = uID;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public boolean isSameUser(String givenUID) {
        return uID.equals(givenUID);
    }

    public String getUID() {
        return uID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String eMail) {
        this.email = eMail;
    }
}
