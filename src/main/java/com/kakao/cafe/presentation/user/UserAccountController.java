package com.kakao.cafe.presentation.user;

import com.kakao.cafe.application.user.UserAccountService;
import com.kakao.cafe.domain.user.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/list")
    public String userList(Model model, HttpServletRequest request) {
        List<UserAccount> users = userAccountService.getAllUser();
        model.addAttribute("users", users);
        model.addAttribute("user-count", users.size());

        if(request.getSession().getAttribute("user-id") != null) {
            return "after/users";
        }
        return "before/users";
    }

    @GetMapping("/{id}/detail")
    public String userInfo(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request) {
        UserAccount userInfo = userAccountService.getUserInfo(id);
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
