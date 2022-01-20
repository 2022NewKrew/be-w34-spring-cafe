package com.kakao.cafe.model.user;

import com.kakao.cafe.service.user.dto.UserRegisterDto;
import com.kakao.cafe.service.user.dto.UserUpdateDto;

public class UserFactory {

    public static User getUser(String userId, String password, String name, String email) {
        return new User(
                new UserId(userId),
                new Password(password),
                new Name(name),
                new Email(email));
    }

    public static User getUser(UserUpdateDto userUpdateDto) {
        return new User(
                new UserId(userUpdateDto.getUserId()),
                new Password(userUpdateDto.getPassword()),
                new Name(userUpdateDto.getName()),
                new Email(userUpdateDto.getEmail()));
    }

    public static User getUser(UserRegisterDto userRegisterDto) {
        return new User(
                new UserId(userRegisterDto.getUserId()),
                new Password(userRegisterDto.getPassword()),
                new Name(userRegisterDto.getName()),
                new Email(userRegisterDto.getEmail()));
    }
}
