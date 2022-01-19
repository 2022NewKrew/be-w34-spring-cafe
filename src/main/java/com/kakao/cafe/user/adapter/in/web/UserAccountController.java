package com.kakao.cafe.user.adapter.in.web;

import com.kakao.cafe.user.application.dto.command.UserAccountDetailIdCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountDetailListResult;
import com.kakao.cafe.user.application.dto.result.UserAccountDetailResult;
import com.kakao.cafe.user.application.port.in.GetUserAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final GetUserAccountUseCase getUserAccountUseCase;

    @GetMapping("/list")
    public String userList(Model model) {
        UserAccountDetailListResult users = getUserAccountUseCase.getAllUser();
        model.addAttribute("users", users.getUserAccountDetailResults());
        model.addAttribute("user-count", users.getUserAccountDetailResults().size());

        return "users";
    }

    @GetMapping("/{user-account-id}/detail")
    public String userInfo(@PathVariable(name = "user-account-id") Long userId, Model model) {
        UserAccountDetailResult userInfo = getUserAccountUseCase.getUserInfo(new UserAccountDetailIdCommand(userId));
        model.addAttribute("userInfo", userInfo);

        return "userInfo";
    }

}
