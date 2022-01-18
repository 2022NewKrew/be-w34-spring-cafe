package com.kakao.cafe.service;

import com.kakao.cafe.domain.dto.UserLoginDTO;
import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.domain.dto.UserViewDTO;
import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.exception.InvalidPasswordException;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signUp(UserSignUpDTO userSignUpDTO) {
        User user = new User(userSignUpDTO.getUserId(),
                userSignUpDTO.getPassword(),
                userSignUpDTO.getName(),
                userSignUpDTO.getEmail());
        userRepository.signUp(user);
    }

    public List<UserViewDTO> findAllUsers() {
        return userRepository.findAllUsers().stream()
                .map(UserViewDTO::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public UserViewDTO findUserById(String userId) {
        User user =  userRepository.findUserByUserId(userId);

        return new UserViewDTO(user);
    }

    public User login(UserLoginDTO userLoginDTO) throws Exception {
        User user =  Optional.ofNullable(userRepository.findUserByUserId(userLoginDTO.getUserId()))
                .orElseThrow(AccountNotFoundException::new);

        if(!user.getPassword().equals(userLoginDTO.getPassword())) {
            throw new InvalidPasswordException();
        }
        return user;
    }
}
