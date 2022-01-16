package com.example.kakaocafe.domain.user;

import com.example.kakaocafe.domain.user.dto.SignUpForm;
import com.example.kakaocafe.core.exception.SignUpException;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserDAO userDAO;

    @Transactional
    public void signUp(SignUpForm signUpForm) {
        validateIfEmailExistThrowException(signUpForm.getEmail());

        userDAO.create(signUpForm);
    }

    private void validateIfEmailExistThrowException(String email) {
        if (userDAO.isExistEmail(email)) {
            throw new SignUpException();
        }
    }
}
