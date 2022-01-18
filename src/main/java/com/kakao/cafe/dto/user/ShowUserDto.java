package com.kakao.cafe.dto.user;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowUserDto {
    private String userId;
    private String email;
    private String name;

    public ShowUserDto(User user) {
        userId = user.getUserId();
        email = user.getEmail();
        name = user.getName();
    }

    @Override
    public String toString() {
        return "ShowUserDto{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
