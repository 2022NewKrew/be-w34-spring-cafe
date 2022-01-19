package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.user.SignupDto;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.exception.DuplicateUserException;
import com.kakao.cafe.exception.LoginFailedException;
import com.kakao.cafe.exception.NoSuchUserException;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.kakao.cafe.utils.DtoConversion;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(SignupDto signupDto) {
        User user = DtoConversion.signupDtoToUser(signupDto);
        if(!userRepository.findUserByName(user.getUserName()).isEmpty()) {
            throw new DuplicateUserException("이미 사용중인 아이디 입니다.");
        }
        userRepository.save(user);
    }

    public List<UserDto> getUserList() {
        return DtoConversion.userListToUserDtoList(userRepository.findAll());
    }

    public User findUserByUserName(UserName userName) {
        return userRepository.findUserByName(userName)
                .orElseThrow(() -> new NoSuchUserException("해당 아이디의 사용자를 찾을 수 없습니다."));
    }

    public UserDto findUserById(UUID id) {
        return DtoConversion.userToUserDto(userRepository.findUserById(id)
                .orElseThrow(() -> new NoSuchUserException("해당 아이디의 사용자를 찾을 수 없습니다.")));
    }

    public UserDto findUserByLoginInfo(UserName userName, Password password) {
        return DtoConversion.userToUserDto(userRepository.findUserByUserNameAndPassword(userName, password)
                .orElseThrow(() -> new LoginFailedException("아이디, 비밀번호를 확인해주세요.")));
    }
}
