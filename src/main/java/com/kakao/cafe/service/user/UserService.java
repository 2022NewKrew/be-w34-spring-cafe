package com.kakao.cafe.service.user;

import com.kakao.cafe.common.exception.custom.UpdateFailedException;
import com.kakao.cafe.common.exception.data.ErrorCode;
import com.kakao.cafe.common.exception.custom.UserNotFoundException;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.UserRepository;
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
        return userRepository.insert(signUpUser);
    }

    public List<UserInfo> getUsersAll() {
        List<User> users = userRepository.findAll();
        return userDtoMapper.toUserInfoList(users);
    }

    public UserInfo getUserInfo(String userId) {
        User findUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        return userDtoMapper.toUserInfo(findUser);
    }

    public UserInfo getUserInfo(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        return userDtoMapper.toUserInfo(findUser);
    }

    public Long update(UserUpdateForm updateForm) {
        User updateUser = userRepository.findByUserId(updateForm.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        if(!updateUser.isCorrectPassword(updateForm.getPassword())) {
            throw new UpdateFailedException(ErrorCode.UPDATE_PASSWORD_INCORRECT);
        }
        updateUser.update(updateForm);
        return userRepository.update(updateUser);
    }

}
