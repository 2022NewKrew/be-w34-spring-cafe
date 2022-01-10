package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.kakao.cafe.CafeApplication.users;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", users);
        return "user_list";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        users.add(user);
        logger.info("[유저 가입] {}", user);
        return "redirect:/users";
    }
}
