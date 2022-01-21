package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.domain.util.TypeConverter;
import com.kakao.cafe.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class UserTableRowDto {
    private final Long id;
    private final String email;
    private final String nickname;
    private final String createdAt;

    public static UserTableRowDto of(User user) {
        return new UserTableRowDto(user.getId(), user.getEmail(), user.getNickname(), TypeConverter.convertLocalDateTimeToString(user.getCreatedAt()));
    }

    public static List<UserTableRowDto> of(List<User> users) {
        return users.stream()
                .map(UserTableRowDto::of)
                .collect(Collectors.toList());
    }
}
