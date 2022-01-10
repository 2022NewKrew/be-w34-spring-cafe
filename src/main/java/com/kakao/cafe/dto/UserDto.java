package com.kakao.cafe.dto;

import com.kakao.cafe.domain.User;

public class UserDto {

    public static class userProfileResponse {
        String userId;
        String name;
        String email;

        public userProfileResponse(String userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
        }

        public static userProfileResponse of(User user) {
            return new userProfileResponse(user.getUserId(), user.getName(), user.getEmail());
        }

        public String getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }
    }

}
