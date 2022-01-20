package com.kakao.cafe.user.service.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.service.UserServiceDTOMapper;


@Getter
public class AllUserProfileServiceResponse {
    private List<UserProfileServiceResponse> allUserProfileList;

    public AllUserProfileServiceResponse(List<User> list) {
        this.allUserProfileList = list.stream()
                                      .map(UserServiceDTOMapper::convertToUserProfileServiceResponse)
                                      .collect(Collectors.toCollection(ArrayList::new));
    }
}
