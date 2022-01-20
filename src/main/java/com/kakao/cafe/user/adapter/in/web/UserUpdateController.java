package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.common.meta.URLPath;
import com.kakao.cafe.user.application.port.in.ModifyingUserRequest;
import com.kakao.cafe.user.application.port.in.ModifyingUserResult;
import com.kakao.cafe.user.application.port.in.UserUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserUpdateController {
    private final UserUpdateUseCase userUpdateUseCase;

    @Autowired
    public UserUpdateController(UserUpdateUseCase userUpdateUseCase) {
        this.userUpdateUseCase = userUpdateUseCase;
    }

    @PostMapping("/users/{userKey}/update")
    public String updateUser(@PathVariable("userKey") Long userId,
                             @Valid @ModelAttribute ModifyingUserRequest modifyingUserRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users/updateForm";
        }
        userUpdateUseCase.updateUser(userId, modifyingUserRequest);
        return URLPath.SHOW_USERS.getRedirectPath();
    }

    @GetMapping("/users/updateForm")
    public String modifyingForm(@SessionAttribute("userKey") Long userId, Model model) {
        ModifyingUserResult modifyingUserResult = userUpdateUseCase.findModifyingUserForm(userId);
        model.addAttribute("user", modifyingUserResult);
        return URLPath.SHOW_USER_UPDATE_FORM.getPath();
    }
}
