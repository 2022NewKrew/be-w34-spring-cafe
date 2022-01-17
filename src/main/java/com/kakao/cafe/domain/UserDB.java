package com.kakao.cafe.domain;

import com.kakao.cafe.DTO.UserInfoDTO;
import com.kakao.cafe.DTO.UserProfileDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private static final List<User> userLst = new ArrayList<>();

    public UserDB() {
        SignUp("skian", "1234", "faust", "faust.like@kakaocorp.com");
        SignUp("dbwhdgus", "1234", "yjh", "dbwhdgus12@naver.com");
    }

    public boolean SignUp(String userId, String password, String name, String email) {
        for (User A : userLst) {
            if (A.getuserId().equals(userId)) {
                return false;
            }
        }

        userLst.add(new User(userId, password, name, email));
        return true;
    }

    public List<UserInfoDTO> getUserInfoLst() {
        List<UserInfoDTO> userInfoLst = new ArrayList<>();
        int idx = 1;

        for (User user : userLst) {
            userInfoLst.add(new UserInfoDTO(idx++, user.getuserId(), user.getName(), user.getEmail()));
        }

        return userInfoLst;
    }

    public UserProfileDTO getUserProfile(String uID) {
        for (User user : userLst) {
            if (user.getuserId().equals(uID)) {
                return new UserProfileDTO(user.getName(), user.getEmail(), user.getPictureAddress());
            }
        }
        return null;
    }
}
