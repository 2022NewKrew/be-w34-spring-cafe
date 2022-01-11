package com.kakao.cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final List<User> users = new ArrayList<>();

    @PostMapping("users")
    public String postSignup(String userId, String password, String name, String email) {
        int uid = users.size() + 1;
        User user = new User(uid, userId, password, name, email);
        users.add(user);
        logger.info("POST request on Signup -> {}", user);
        return "redirect:users";
    }

    @GetMapping("users")
    public String getUserList(Model model) {
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("users/{targetUserId}")
    public String getUserProfile(@PathVariable("targetUserId") String targetUserId, Model model) {
        User user = getUserFromUserId(targetUserId);
        model.addAttribute( "userId", user.getUserId());
        model.addAttribute( "usename", user.getName());
        model.addAttribute( "email", user.getEmail());
        return "user/profile";
    }

    private User getUserFromUserId(String targetUserId) {
        Optional<User> targetUser = users.stream()
            .filter(user -> user.getUserId().equals(targetUserId))
            .findFirst();

        if (targetUser.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] '" + targetUserId + "'로 등록된 사용자가 없습니다.");
        }
        return targetUser.get();
    }
}
