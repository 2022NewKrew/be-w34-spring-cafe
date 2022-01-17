package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.common.meta.URLPath;
import com.kakao.cafe.user.application.port.in.ModifyingUserInfo;
import com.kakao.cafe.user.application.port.in.UserUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserUpdateController {
    private final UserUpdateUseCase userUpdateUseCase;

    @PostMapping("/users/{userId}/update")
    public String updateUser(@PathVariable("userId") Long userId,
                             @Valid @ModelAttribute ModifyingUserInfo modifyingUserInfo,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users/" + userId + "/form";
        }
        userUpdateUseCase.updateUser(userId, modifyingUserInfo);
        return URLPath.SHOW_USERS.getRedirectPath();
    }

    @GetMapping("/users/{userId}/form")
    public String modifyingForm(@PathVariable("userId") Long userId, Model model) {
        ModifyingUserInfo modifyingUserInfo = userUpdateUseCase.findModifyingUserForm(userId);
        model.addAttribute("user", modifyingUserInfo);
        return URLPath.SHOW_USER_UPDATE_FORM.getPath();
    }


}
