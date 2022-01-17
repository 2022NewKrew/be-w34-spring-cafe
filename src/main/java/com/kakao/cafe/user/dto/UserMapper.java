package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserMapper implements RowMapper<User> {

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

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getUserId(), user.getName(), user.getEmail());
    }

    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new User(
                new UserId(resultSet.getString("USER_ID")),
                new Password(resultSet.getString("PASSWORD")),
                new Name(resultSet.getString("NAME")),
                new Email(resultSet.getString("EMAIL")));
    }
}
