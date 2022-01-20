package com.kakao.cafe.service;

import com.kakao.cafe.controller.UserController;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserRequestDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        userRepository.save(user);
    }

    public List<UserDto> getUserList() {
        return userRepository.findAll()
                        .stream()
                        .map(UserDto::entityToDto)
                        .collect(Collectors.toList());
    }

    public UserDto findById(String userId){
        return UserDto.entityToDto(userRepository.findById(userId));
    }

    public void login(UserRequestDto userRequestDto, HttpSession httpSession) throws IllegalArgumentException {
        User retrievedUser = userRepository.findByMail(userRequestDto.getEmail());

        if (retrievedUser == null)
            throw new IllegalArgumentException("올바른 메일을 입력해주세요");
        if (!userRequestDto.getPassword().equals(retrievedUser.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");

        httpSession.setAttribute("sessionedUser", retrievedUser);
    }

    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
