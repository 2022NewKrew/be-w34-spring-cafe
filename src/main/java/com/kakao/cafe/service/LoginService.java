package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.LoginUserDto;
import com.kakao.cafe.repository.UserDAOInterface;
import com.kakao.cafe.util.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    private final UserDAOInterface userDAO;
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    public LoginService(UserDAOInterface userDAO) {
        this.userDAO = userDAO;
    }

    public void loginCheck(LoginUserDto loginUserDto, HttpSession session) {
        String inputId = loginUserDto.getUserId();
        String inputPassword = loginUserDto.getPassword();
        User user = userDAO.findById(inputId);
        if (user == null) {
            logger.info("{} login fail", inputId);
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }
        if (!isPasswordCorrect(user, inputPassword)) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }
        logger.info("{} login success", inputId);
        session.setAttribute("sessionedUser", userDAO.findById(inputId));
        logger.info("{} session created", inputId);
    }

    private boolean isPasswordCorrect(User savedUser, String inputPassword) {
        String SHAInputId = SHA256.encrypt(inputPassword);
        return SHAInputId.equals(savedUser.getPassword());
    }
}
