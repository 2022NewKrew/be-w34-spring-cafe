package com.kakao.cafe.module.service;

import com.kakao.cafe.infra.exception.DuplicateUserException;
import com.kakao.cafe.infra.exception.NoSuchDataException;
import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.kakao.cafe.module.model.dto.UserDtos.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void signUp(UserSignUpDto userSignUpDto) {
        validateDuplicateUserId(userSignUpDto.getUserId());
        validateDuplicateName(userSignUpDto.getName());
        userRepository.addUser(modelMapper.map(userSignUpDto, User.class));
    }

    public UserDto signIn(UserSignInDto userSignInDto) {
        User user = userRepository.findUserByUserId(userSignInDto.getUserId())
                .orElseThrow(() -> new NoSuchDataException("해당하는 아이디의 유저가 없습니다."));
        validatePassword(user.getPassword(), userSignInDto.getPassword());
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> userList() {
        return userRepository.findAllUsers().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto findUser(Long id) {
        return modelMapper.map(userRepository.findUserById(id), UserDto.class);
    }

    public void updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findUserById(id);
        validatePassword(user.getPassword(), userUpdateDto.getPassword());
        if (!user.getName().equals(userUpdateDto.getName())) {
            validateDuplicateName(userUpdateDto.getName());
        }

        User updateUserInfo = new User(id, user.getUserId(), userUpdateDto.getNewPassword(), userUpdateDto.getName(), userUpdateDto.getEmail());
        userRepository.updateUser(updateUserInfo);
    }

    private void validateDuplicateName(String name) {
        duplicateUser(userRepository.findUserByName(name).isPresent(), "이미 존재하는 이름입니다.");
    }

    private void validateDuplicateUserId(String userId) {
        duplicateUser(userRepository.findUserByUserId(userId).isPresent(), "이미 존재하는 유저 아이디입니다.");
    }

    private void duplicateUser(boolean isDuplicated, String msg) {
        if (isDuplicated) {
            throw new DuplicateUserException(msg);
        }
    }

    private void validatePassword(String password, String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
