package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.UpdateRequest;
import com.kakao.cafe.application.user.port.in.UpdateUserInfoUseCase;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserUpdateController {

    private final UpdateUserInfoUseCase updateUserInfoUseCase;

    public UserUpdateController(UpdateUserInfoUseCase updateUserInfoUseCase) {
        this.updateUserInfoUseCase = updateUserInfoUseCase;
    }

    @PostMapping("/users/{userId}/form")
    public String update(@PathVariable String userId, UpdateRequest updateRequest)
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException, UserNotExistException, WrongPasswordException {
        updateUserInfoUseCase.updateUserInfo(userId, updateRequest);
        return "redirect:/users";
    }
}
