package domain;

import java.time.LocalDate;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String registerDate;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = LocalDate.now().toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registerDate='" + registerDate + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }
}
