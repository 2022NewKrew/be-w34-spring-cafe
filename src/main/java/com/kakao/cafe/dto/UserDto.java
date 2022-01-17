package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;

public class UserDto {

    public static class UserProfileResponse {
        private String userId;
        private String name;
        private String email;

        public UserProfileResponse(String userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
        }

        public static UserProfileResponse of(User user) {
            return new UserProfileResponse(user.getUserId(), user.getName(), user.getEmail());
        }

        public String getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }
    }

    public static class CreateUserProfileRequest {
        private String userId;
        private String password;
        private String email;
        private String name;

        public User toUserEntity() {
            return new User(userId, password, name, email);
        }

        public String getUserId() {
            return userId;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class UpdateUserProfileRequest {
        private String originPassword;
        private String changedPassword;
        private String email;
        private String name;

        public String getOriginPassword() {
            return originPassword;
        }

        public String getChangedPassword() {
            return changedPassword;
        }

        public String getEmail() {
            return email;
        }

        public User toUserEntity(String userId) {
            return new User(userId, changedPassword, name, email);
        }

        public String getName() {
            return name;
        }

        public void setOriginPassword(String originPassword) {
            this.originPassword = originPassword;
        }

        public void setChangedPassword(String changedPassword) {
            this.changedPassword = changedPassword;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class LoginDto {
        private String userId;
        private String password;

        public String getUserId() {
            return userId;
        }

        public String getPassword() {
            return password;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class UserSessionDto {
        private String userId;
        private String name;

        public UserSessionDto(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        public static UserSessionDto of(User user) {
            return new UserSessionDto(user.getUserId(), user.getName());
        }

        public String getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }
    }
}
