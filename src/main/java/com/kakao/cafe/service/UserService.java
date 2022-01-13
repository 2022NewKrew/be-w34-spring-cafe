package com.kakao.cafe.service;

import com.kakao.cafe.DTO.SignInDTO;
import com.kakao.cafe.DTO.UserInfoDTO;
import com.kakao.cafe.DTO.UserProfileDTO;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDB;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final UserDB userDB = new UserDB();

    public static List<UserInfoDTO> getUserInfoLst() {
        List<User> userLst = userDB.getUserLst();
        List<UserInfoDTO>  userInfoDTOLst = new ArrayList<>();
        int idx = 1;

        for (User curUser : userLst) {
            userInfoDTOLst.add(makeUserInfoDTOFromUser(curUser, idx));
        }

        return userInfoDTOLst;
    }

    public static UserProfileDTO getUserProfile(String userId) {
        return makeUserProfileDTOFromUser(userDB.getUser(userId));
    }

    public static boolean SignUp(SignInDTO candidate) {
        return userDB.SignUp(candidate.getUserId(), candidate.getPassword(), candidate.getName(), candidate.getEmail());
    }

    private static UserInfoDTO makeUserInfoDTOFromUser(User userObj, int idx) {
        return new UserInfoDTO(idx, userObj.getuserId(), userObj.getName(), userObj.getEmail());
    }

    private static UserProfileDTO makeUserProfileDTOFromUser(User userObj) {
        return new UserProfileDTO(userObj.getName(), userObj.getEmail(), userObj.getPictureAddress());
    }
}
