package com.example.kakaocafe.domain.user;

import com.example.kakaocafe.domain.user.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserDAO userDAO;

    public User login(LoginForm loginForm) throws LoginException {

        final User user = userDAO.findByEmail(loginForm.getEmail())
                .orElseThrow(LoginException::new);

        user.validatePassword(loginForm.getPassword());

        return user;
    }
}
