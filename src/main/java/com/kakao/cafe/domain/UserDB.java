package com.kakao.cafe.domain;

import com.kakao.cafe.DTO.UserInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private static final List<User> userLst = new ArrayList<>();

    public UserDB() {
        SignUp("skian", "1234", "faust", "faust.like@kakaocorp.com");
        SignUp("dbwhdgus", "1234", "yjh", "dbwhdgus12@naver.com");
    }

    public boolean SignUp(String uID, String password, String name, String email) {
        for (User A : userLst) {
            if (A.getUID().equals(uID)) {
                return false;
            }
        }

        userLst.add(new User(uID, password, name, email));
        return true;
    }

    public List<UserInfoDTO> getUserInfoLst() {
        List<UserInfoDTO> userInfoLst = new ArrayList<>();
        int idx = 1;

        for (User user : userLst) {
            userInfoLst.add(new UserInfoDTO(idx++, user.getUID(), user.getName(), user.getEmail()));
        }

        return userInfoLst;
    }
}
