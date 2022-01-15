package com.example.kakaocafe.security.service;

import com.example.kakaocafe.domain.user.User;
import com.example.kakaocafe.domain.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserDAO userDAO;

    public User login(String email, String password) throws LoginException {
        final User user = userDAO.findByEmail(email)
                .orElseThrow(LoginException::new);

        user.validatePassword(password);
        return user;
    }
}
