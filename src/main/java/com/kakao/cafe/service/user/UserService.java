package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.Entity.User;
import com.kakao.cafe.domain.Repository.user.UserRepository;
import com.kakao.cafe.dto.user.LoginDto;
import com.kakao.cafe.dto.user.SignUpDto;
import com.kakao.cafe.dto.user.UserInfoDto;
import com.kakao.cafe.exceptions.NoSuchUserException;
import com.kakao.cafe.exceptions.PasswordMismatchException;
import com.kakao.cafe.exceptions.UserIdDuplicationException;
import com.kakao.cafe.exceptions.WrongAccessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(SignUpDto signUpDto) throws UserIdDuplicationException {
        if (this.userRepository.isUserIdExist(signUpDto.getUserId())) {
            throw new UserIdDuplicationException();
        }
        this.userRepository.save(signUpDto.toEntity());
    }

    public List<UserInfoDto> findAll() {
        List<UserInfoDto> userList = this.userRepository.findAll().stream()
                .map(UserInfoDto::new).collect(Collectors.toList());
        return userList;
    }

    public UserInfoDto findById(String userId) throws NoSuchUserException {
        User user = this.userRepository.findById(userId);
        return new UserInfoDto(user);
    }

    public void update(SignUpDto signUpDto) throws NoSuchUserException, PasswordMismatchException {
        User targetUser = this.userRepository.findById(signUpDto.getUserId());
        if (!targetUser.getPassword().equals(signUpDto.getPassword())) {
            throw new PasswordMismatchException();
        }
        this.userRepository.update(signUpDto.getUserId(), signUpDto.getName(), signUpDto.getEmail());
    }

    public UserInfoDto login(LoginDto loginDto) throws NoSuchUserException, PasswordMismatchException {
        User user = this.userRepository.findById(loginDto.getUserId());
        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new PasswordMismatchException();
        }
        return new UserInfoDto(user);
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

    public boolean isUserLoggedin(HttpSession session) {
        return session.getAttribute("sessionedUser") != null;
    }

    public void userValidation(String userId, HttpSession session) throws WrongAccessException {
        UserInfoDto sessionedUser = (UserInfoDto) session.getAttribute("sessionedUser");
        if (!userId.equals(sessionedUser.getUserId())) {
            throw new WrongAccessException();
        }
    }

}
