package com.kakao.cafe.dto;

import com.kakao.cafe.domain.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toUser(UserFormDto userFormDto) {
        UserId userId = new UserId(userFormDto.getUserId());
        Password password = new Password(userFormDto.getPassword());
        Name name = new Name(userFormDto.getName());
        Email email = new Email(userFormDto.getEmail());
        return new User(userId, password, name, email);
    }

    public static List<UserDto> toListUserDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getUserId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public static UserProfileDto toUserProfileDto(User user) {
        return new UserProfileDto(user.getName(), user.getEmail());
    }
}
