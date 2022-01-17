package com.kakao.cafe.service;

import com.kakao.cafe.dto.LoginUserDto;
import com.kakao.cafe.repository.UserDAOInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    private final UserDAOInterface userDAO;
    Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    public LoginService(UserDAOInterface userDAO) {
        this.userDAO = userDAO;
    }

    public void loginCheck(LoginUserDto loginUserDto, HttpSession session) {
        String inputId = loginUserDto.getUserId();
        String inputPassword = loginUserDto.getPassword();
        if (!isPasswordCorrect(inputId, inputPassword)) {
            logger.info("{} login fail", inputId);
            return;
        }
        logger.info("{} login success", inputId);
        session.setAttribute("Id", inputId);
    }

    private boolean isPasswordCorrect(String inputId, String inputPassword) {
        String savedPassword = userDAO.findById(inputId).getPassword();
        return inputPassword.equals(savedPassword);
    }
}
