package com.kakao.cafe.user.service.dto;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.service.UserServiceDTOMapper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
public class AllUserProfileServiceResponse {
    private ArrayList<UserProfileServiceResponse> allUserProfileList;

    public AllUserProfileServiceResponse(ArrayList<User> list) {
        this.allUserProfileList = list.stream().map(UserServiceDTOMapper::convertToUserProfileServiceResponse).collect(Collectors.toCollection(ArrayList::new));
    }
}
