package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.service.user.dto.UserIdentification;
import com.kakao.cafe.service.user.dto.UserInfo;
import com.kakao.cafe.service.user.dto.UserSignUpForm;
import com.kakao.cafe.service.user.dto.UserUpdateForm;
import com.kakao.cafe.service.user.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public Long signUp(UserSignUpForm userSignUpForm) {
        User signUpUser = User.of(userSignUpForm.getUserId(), userSignUpForm.getPassword(), userSignUpForm.getUserName(), userSignUpForm.getEmail());
        return userRepository.insertUser(signUpUser);
    }

    public List<UserInfo> getUsersAll() {
        List<User> users = userRepository.findAll();
        return userDtoMapper.toUserInfoList(users);
    }

    public UserInfo getUserInfo(String userId) {
        User findUser = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return userDtoMapper.toUserInfo(findUser);
    }

    public UserInfo getUserInfo(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return userDtoMapper.toUserInfo(findUser);
    }

    public Long updateUser(UserUpdateForm updateForm) {
        User updateUser = userRepository.findByUserId(updateForm.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        updateUser.update(updateForm);
        return userRepository.updateUser(updateUser);
    }

    public UserIdentification login(String userId, String password) {
        User loginUser = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        if(!loginUser.getPassword().equals(password)) {
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }
        return userDtoMapper.toUserIdentification(loginUser);
    }

}
