package com.kakao.cafe.domain.users;

public class Users {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public Users(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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

//    @Override
//    public String toString() {
//        return "Users{"
//                + "userId='"
//                + userId
//                + "password='"
//                + password
//                + "name='"
//                + name
//                +'\''
//                +", email='"
//                + email
//                + '}';
//    }
}
