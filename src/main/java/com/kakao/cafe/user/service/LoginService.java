package com.kakao.cafe.user.service;

import com.kakao.cafe.exception.InvalidUsernamePasswordException;
import com.kakao.cafe.exception.UnauthorizedAccessException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.LoggedInUser;
import com.kakao.cafe.user.dto.UserLoginForm;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoggedInUser login(UserLoginForm userLoginForm) {
        User user = userRepository.get(userLoginForm.getUsername()).orElseThrow(InvalidUsernamePasswordException::new);
        if (user.getPassword().equals(userLoginForm.getPassword())) {
            return new LoggedInUser(user.getId(), user.getUsername());
        }
        throw new InvalidUsernamePasswordException();
    }

    public LoggedInUser getLoggedInUser(HttpSession session) {
        LoggedInUser loggedInUser = (LoggedInUser) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            throw new UnauthorizedAccessException();
        }
        return loggedInUser;
    }
}
