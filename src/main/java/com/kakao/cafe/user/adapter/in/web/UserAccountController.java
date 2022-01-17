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
    public String userList(Model model, HttpServletRequest request) {
        UserAccountDetailListResult users = getUserAccountUseCase.getAllUser();
        model.addAttribute("users", users.getUserAccountDetailResults());
        model.addAttribute("user-count", users.getUserAccountDetailResults().size());

        if(request.getSession().getAttribute("user-id") != null) {
            return "after/users";
        }
        return "before/users";
    }

    @GetMapping("/{id}/detail")
    public String userInfo(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request) {
        UserAccountDetailResult userInfo = getUserAccountUseCase.getUserInfo(new UserAccountDetailIdCommand(id));
        model.addAttribute("userInfo", userInfo);

        if(request.getSession().getAttribute("user-id") != null) {
            return "after/userinfo";
        }
        return "before/userInfo";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user-id");
        return "redirect:/";
    }
}
