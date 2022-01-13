package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
public class AllUsersResponseDTO {
    public ArrayList<OneUserDataDTO> allUserDataList;

    public AllUsersResponseDTO(ArrayList<User> list) {
        this.allUserDataList = list.stream().map(user -> new OneUserDataDTO(user.getNickName(), user.getEmail(), user.getSignUpDate().toString(), user.getStringId())).collect(Collectors.toCollection(ArrayList::new));
    }

    @AllArgsConstructor
    @Getter
    public static class OneUserDataDTO {
        public String nickName;
        public String email;
        public String signUpDate;
        public String stringId;
    }
}
