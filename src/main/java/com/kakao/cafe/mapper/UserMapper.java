package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;
import com.kakao.cafe.dto.ProfileResponseDto;
import com.kakao.cafe.dto.SignupRequestDto;
import com.kakao.cafe.dto.UserListResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User signupRequestDtoToUser(SignupRequestDto dto) {
        UserId userId = new UserId(dto.getUserId());
        Password password = new Password(dto.getPassword());
        Name name = new Name(dto.getName());
        Email email = new Email(dto.getEmail());
        return new User(userId, password, name, email);
    }

    public UserListResponseDto userToUserListResponseDto(User user) {
        String userId = user.getId().getValue();
        String name = user.getName().getValue();
        String email = user.getEmail().getValue();
        return new UserListResponseDto(userId, name, email);
    }

    public ProfileResponseDto userToProfileResponseDto(User user) {
        String name = user.getName().getValue();
        String email = user.getEmail().getValue();
        return new ProfileResponseDto(name, email);
    }
}
