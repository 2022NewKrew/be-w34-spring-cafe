package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.application.user.port.in.SignUpUserUseCase;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserIdDuplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSignUpController {

    private static final Logger log = LoggerFactory.getLogger(UserSignUpController.class);

    private final SignUpUserUseCase signUpUserUseCase;

    public UserSignUpController(SignUpUserUseCase signUpUserUseCase) {
        this.signUpUserUseCase = signUpUserUseCase;
    }

    @PostMapping("/users")
    public String signUp(SignUpRequest signUpRequest)
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, UserIdDuplicationException, IllegalEmailException {
        signUpUserUseCase.signUpUser(signUpRequest);
        log.info("{} joined as a member", signUpRequest.getUserId());
        return "redirect:/users";
    }
}
