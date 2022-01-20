package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.UserLoginDto;
import com.kakao.cafe.domain.dto.UserSignUpDto;
import com.kakao.cafe.domain.dto.UserViewDto;
import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.exception.InvalidPasswordException;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(UserSignUpDto userSignUpDTO) {
        User user = new User(userSignUpDTO.getUserId(),
                userSignUpDTO.getPassword(),
                userSignUpDTO.getName(),
                userSignUpDTO.getEmail());
        userRepository.signUp(user);
    }

    public List<UserViewDto> findAllUsers() {
        return userRepository.findAllUsers().stream()
                .map(UserViewDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public UserViewDto findUserById(String userId) {
        User user =  userRepository.findUserByUserId(userId);

        return new UserViewDto(user);
    }

    public User login(UserLoginDto userLoginDTO) throws Exception {
        User user =  Optional.ofNullable(userRepository.findUserByUserId(userLoginDTO.getUserId()))
                .orElseThrow(AccountNotFoundException::new);

        if(!user.getPassword().equals(userLoginDTO.getPassword())) {
            throw new InvalidPasswordException();
        }
        return user;
    }
}
