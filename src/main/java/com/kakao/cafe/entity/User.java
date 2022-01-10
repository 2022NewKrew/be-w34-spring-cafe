package com.kakao.cafe.entity;

public class User implements Entity<com.kakao.cafe.dto.User> {

    private final String id;
    private final String name;
    private final String password;
    private final String email;

    private User(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public com.kakao.cafe.dto.User toDto() {
        return new com.kakao.cafe.dto.User.Builder()
                .email(email)
                .id(id)
                .name(name)
                .build();
    }

    public static class Builder {

        private String id;
        private String name;
        private String password;
        private String email;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(id, name, password, email);
        }
    }
}
