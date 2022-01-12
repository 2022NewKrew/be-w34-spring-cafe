package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.user.ProfileResponseDto;
import com.kakao.cafe.dto.user.SignupRequestDto;
import com.kakao.cafe.dto.user.UserListResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User signupRequestDtoToUser(SignupRequestDto dto) {
        UserName userName = new UserName(dto.getUserName());
        Password password = new Password(dto.getPassword());
        Name name = new Name(dto.getName());
        Email email = new Email(dto.getEmail());
        return new User(userName, password, name, email);
    }

    public UserListResponseDto userToUserListResponseDto(User user) {
        String id = user.getId().toString();
        String userName = user.getUserName().getValue();
        String name = user.getName().getValue();
        String email = user.getEmail().getValue();
        return new UserListResponseDto(id, userName, name, email);
    }

    public ProfileResponseDto userToProfileResponseDto(User user) {
        String name = user.getName().getValue();
        String email = user.getEmail().getValue();
        return new ProfileResponseDto(name, email);
    }
}
