package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
public class AllUserViewDTO {
    public ArrayList<OneUserDataDTO> allUserDataList;

    public AllUserViewDTO(ArrayList<User> list) {
        this.allUserDataList = list.stream().map(user -> new OneUserDataDTO(user.getNickName(), user.getEmail(), user.getSignUpDate().toString())).collect(Collectors.toCollection(ArrayList::new));
    }

    @AllArgsConstructor
    public static class OneUserDataDTO {
        public String nickName;
        public String email;
        public String signUpDate;
    }
}
