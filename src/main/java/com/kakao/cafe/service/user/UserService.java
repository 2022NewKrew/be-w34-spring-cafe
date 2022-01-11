package com.kakao.cafe.service.user;

import com.kakao.cafe.controller.users.dto.UserInfoDto;
import com.kakao.cafe.controller.users.dto.UserItemDto;
import com.kakao.cafe.controller.users.dto.UserProfileDto;
import com.kakao.cafe.controller.users.mapper.UserDtoMapper;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.service.user.dto.UserUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(
            @Qualifier("memoryUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long signUp(String userId, String password, String userName, String email) {
        User signUpUser = User.of(userId, password, userName, email);
        return userRepository.insertUser(signUpUser);
    }

    public List<UserItemDto> getUsersAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDtoMapper::toUserItemDto)
                .collect(Collectors.toList());
    }

    public UserProfileDto getUserProfile(String userId) {
        User findUser = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return UserDtoMapper.toUserProfileDto(findUser);
    }

    public UserInfoDto getUserInfo(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return UserDtoMapper.toUserInfoDto(findUser);
    }

    public Long updateUser(UserUpdateForm updateForm) {
        User updateUser = userRepository.findById(updateForm.getId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        updateUser.update(updateForm);
        return userRepository.updateUser(updateUser);
    }

}
