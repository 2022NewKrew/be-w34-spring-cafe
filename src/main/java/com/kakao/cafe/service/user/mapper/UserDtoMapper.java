package com.kakao.cafe.service.user.mapper;

import com.kakao.cafe.controller.users.dto.request.UserUpdateRequest;
import com.kakao.cafe.service.user.dto.UserInfo;
import com.kakao.cafe.service.user.dto.UserSignUpForm;
import com.kakao.cafe.service.user.dto.UserUpdateForm;
import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

    public UserSignUpForm toUserSignForm(String userId, String password, String userName, String email) {
        return UserSignUpForm.builder()
                .userId(userId)
                .password(password)
                .userName(userName)
                .email(email).build();
    }

    public UserUpdateForm toUserUpdateForm(UserUpdateRequest updateRequest) {
        return UserUpdateForm.builder()
                .userId(updateRequest.getUserId())
                .password(updateRequest.getPassword())
                .userName(updateRequest.getName())
                .email(updateRequest.getEmail()).build();
    }

    public List<UserInfo> toUserInfoList(List<User> userList){
        return userList.stream()
                .map(user -> toUserInfo(user))
                .collect(Collectors.toList());
    }

    public UserInfo toUserInfo(User user){
        return UserInfo.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }

}
