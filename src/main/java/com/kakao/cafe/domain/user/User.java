package com.kakao.cafe.domain.user;

import com.kakao.cafe.model.UserModel;

public class User {
    private final Email email;
    private final ID id;
    private final Name name;
    private final Password password;

    public User(Email email, ID id, Name name, Password password){
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String email, String id, String name, String password){
        this.email = new Email(email);
        this.id = new ID(id);
        this.name = new Name(name);
        this.password = new Password(password);
    }

    public User(UserModel userModel){
        this(userModel.getEmail(), userModel.getUserId(), userModel.getName(), userModel.getPassword());
    }

    private User(Builder builder){
        this(builder.email, builder.id, builder.name, builder.password);
    }


    public boolean isUserId(String userId){
        return id.is(userId);
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getId() {
        return id.getId();
    }

    public String getName() {
        return name.getName();
    }

    public String getPassword() {
        return password.getPassword();
    }


    boolean canModify(String userId, String password){
        return this.id.is(userId) && this.password.is(password);
    }

    public static class Builder{
        String email;
        String id;
        String name;
        String password;

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

}
