package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.*;
import com.kakao.cafe.dto.UserDetailDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserFormDto;
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
        return new User(
                new UserId(userFormDto.getUserId()),
                new Password(userFormDto.getPassword()),
                new Name(userFormDto.getName()),
                new Email(userFormDto.getEmail())
        );
    }

    public static List<UserDto> toListUserDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(
                        user.getUserId().getUserId(),
                        user.getName().getName(),
                        user.getEmail().getEmail())
                ).collect(Collectors.toList());
    }

    public static UserDetailDto toUserProfileDto(User user) {
        return new UserDetailDto(user.getName().getName(), user.getEmail().getEmail());
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getUserId().getUserId(),
                user.getName().getName(),
                user.getEmail().getEmail()
        );
    }

    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new User(
                new UserId(resultSet.getString("USER_ID")),
                new Password(resultSet.getString("PASSWORD")),
                new Name(resultSet.getString("NAME")),
                new Email(resultSet.getString("EMAIL"))
        );
    }
}
