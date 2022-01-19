package com.kakao.cafe.domain.user;

public class User {

    private UserId userId;
    private Password password;
    private Name name;
    private Email email;

    public UserId getUserId() {
        return userId;
    }

    public Password getPassword() {
        return password;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof User)) {
            return false;
        }

        User user = (User) obj;
        return userId.equals(user.getUserId()) &&
                password.equals(user.getPassword()) &&
                name.equals(user.getName()) &&
                email.equals(user.getEmail());
    }
}
