package com.kakao.cafe.service;

import com.kakao.cafe.DTO.SignInDTO;
import com.kakao.cafe.DTO.UserInfoDTO;
import com.kakao.cafe.DTO.UserProfileDTO;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public List<UserInfoDTO> getUserInfoLst() {
        List<User> userLst = userRepo.getUserLst();
        List<UserInfoDTO> userInfoDTOLst = new ArrayList<>();
        int idx = 1;

        for (User curUser : userLst) {
            userInfoDTOLst.add(makeUserInfoDTOFromUser(curUser, idx));
        }

        return userInfoDTOLst;
    }

    public UserProfileDTO getUserProfile(String userId) {
        return makeUserProfileDTOFromUser(userRepo.getUser(userId));
    }

    public boolean SignUp(SignInDTO candidate) {
        return userRepo.SignUp(candidate.getUserId(), candidate.getPassword(), candidate.getName(), candidate.getEmail());
    }

    private UserInfoDTO makeUserInfoDTOFromUser(User userObj, int idx) {
        return new UserInfoDTO(idx, userObj.getUserId(), userObj.getName(), userObj.getEmail());
    }

    private UserProfileDTO makeUserProfileDTOFromUser(User userObj) {
        return new UserProfileDTO(userObj.getName(), userObj.getEmail(), userObj.getPictureAddress());
    }
}
