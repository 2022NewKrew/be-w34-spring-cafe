package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.UpdateRequest;
import com.kakao.cafe.application.user.port.in.UpdateUserInfoUseCase;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserUpdateController {

    private static final Logger log = LoggerFactory.getLogger(UserUpdateController.class);

    private final UpdateUserInfoUseCase updateUserInfoUseCase;

    public UserUpdateController(UpdateUserInfoUseCase updateUserInfoUseCase) {
        this.updateUserInfoUseCase = updateUserInfoUseCase;
    }

    @PostMapping("/users/{userId}/form")
    public String update(HttpServletRequest request, @PathVariable String userId, UpdateRequest updateRequest)
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException, UserNotExistException, WrongPasswordException {
        log.info("[{}]User {} required info update", request.getRequestURI(), userId);
        updateUserInfoUseCase.updateUserInfo(userId, updateRequest);
        log.info("[{}]Success user {} info update", request.getRequestURI(), userId);
        return "redirect:/users";
    }
}
