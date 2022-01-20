package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 회원에 리스트에 대한 DTO 입니다.
 *
 * @author jm.hong
 */
@Getter
@Setter
public class UserDto {
    private Long id;
    private String userId;
    private String name;
    private String email;
    private UserStatus role;

    public static UserDto of(User user) {

        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public static List<UserDto> of(List<User> users) {
        return users
                .stream()
                .map(UserDto::of).collect(Collectors.toList());
    }
}
