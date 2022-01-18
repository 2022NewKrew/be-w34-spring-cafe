package com.kakao.cafe.auth.service;

import com.kakao.cafe.auth.exception.IdPasswordMismatchException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public void validatePassword(String userPassword, String requestPassword){
        if(!userPassword.equals(requestPassword)){
            throw new IdPasswordMismatchException();
        }
    }
}
