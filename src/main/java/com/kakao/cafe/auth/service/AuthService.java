package com.kakao.cafe.auth.service;

import com.kakao.cafe.auth.dto.LoginRequest;
import com.kakao.cafe.auth.exception.IdPasswordMismatchException;
import com.kakao.cafe.user.exception.UserNotExistException;
import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private HttpSession session;

    public void login(LoginRequest loginRequest){
        User user = userRepository.findOneByUserId(loginRequest.getUserId())
                .orElseThrow(UserNotExistException::new);
        validatePassword(loginRequest.getPassword(), user.getPassword());

        if (!(session.getAttribute("LOGIN_USER_ID") == (user.getUserId()))) {
            session.setAttribute("LOGIN_USER_ID", user.getUserId());
        }
    }

    public void logout(){
        session.removeAttribute("LOGIN_USER_ID");
    }

    public void validatePassword(String userPassword, String requestPassword){
        if(!userPassword.equals(requestPassword)){
            throw new IdPasswordMismatchException();
        }
    }
}
