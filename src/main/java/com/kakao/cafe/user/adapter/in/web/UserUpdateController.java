package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.common.meta.URLPath;
import com.kakao.cafe.user.application.port.in.ModifyingUserInfo;
import com.kakao.cafe.user.application.port.in.UserUpdateUseCase;
import lombok.RequiredArgsConstructor;
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
                             @Valid @ModelAttribute ModifyingUserInfo modifyingUserInfo,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users/updateForm";
        }
        userUpdateUseCase.updateUser(userId, modifyingUserInfo);
        return URLPath.SHOW_USERS.getRedirectPath();
    }

    @GetMapping("/users/updateForm")
    public String modifyingForm(@SessionAttribute("userKey") Long userId, Model model) {
        ModifyingUserInfo modifyingUserInfo = userUpdateUseCase.findModifyingUserForm(userId);
        model.addAttribute("user", modifyingUserInfo);
        return URLPath.SHOW_USER_UPDATE_FORM.getPath();
    }


}
