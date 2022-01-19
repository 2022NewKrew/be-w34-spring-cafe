package com.kakao.cafe.user.service;

import com.kakao.cafe.user.dto.request.LoginReq;
import com.kakao.cafe.user.dto.request.SignUpReq;
import com.kakao.cafe.user.dto.request.UserUpdateReq;
import com.kakao.cafe.user.dto.response.UserDto;
import com.kakao.cafe.user.exception.LoginFailException;
import com.kakao.cafe.user.exception.UserIdDuplicateException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.persistence.User;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void signUp(SignUpReq req) {
        Optional<User> user = userRepository.findByUserId(req.getUserId());
        if(user.isPresent())
            throw new UserIdDuplicateException();

        userRepository.save(userMapper.signUpReqToEntity(req));
    }

    public List<UserDto> getUserList() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public UserDto getProfileById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.userToDto(user.orElseThrow(UserNotFoundException::new));
    }

    public UserDto getLoginUser(LoginReq req) {
        Optional<User> user = userRepository.findByUserIdAndPassword(req.getUserId(), req.getPassword());
        return userMapper.userToDto(user.orElseThrow(LoginFailException::new));
    }

    public UserDto updateUser(Long id, UserUpdateReq req) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        user.setPassword(req.getNewPassword());
        user.setName(req.getName());
        user.setEmail(req.getEmail());

        userRepository.update(user);
        return userMapper.userToDto(user);
    }
}
