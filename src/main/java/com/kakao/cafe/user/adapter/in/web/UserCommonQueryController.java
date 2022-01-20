package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.common.meta.URLPath;
import com.kakao.cafe.user.application.port.in.UserCommonQueryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserCommonQueryController {

    private final UserCommonQueryUseCase userCommonQueryUseCase;

    @Autowired
    public UserCommonQueryController(UserCommonQueryUseCase userCommonQueryUseCase) {
        this.userCommonQueryUseCase = userCommonQueryUseCase;
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userCommonQueryUseCase.findUserInventoryInfo());
        return URLPath.SHOW_USER_LIST.getPath();
    }
}
