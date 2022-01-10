package com.kakao.cafe.dto;

public class User implements Dto<com.kakao.cafe.entity.User> {

    private final String id;
    private final String name;
    private final String email;

    private User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public com.kakao.cafe.entity.User toEntity() {
        return new com.kakao.cafe.entity.User.Builder()
                .email(email)
                .id(id)
                .name(name)
                .build();
    }

    public static class Builder {

        private String id;
        private String name;
        private String email;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(id, name, email);
        }
    }
}
