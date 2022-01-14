package com.kakao.cafe.Controller;

import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String join(User user) {
        userService.join(user);
        logger.info("POST /users", user.getNickName());
        logger.info("{}(nickname) joined.", user.getNickName());
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getList(Model model) {
        logger.info("GET /users: Get all users.");
        List<User> findUsers = userService.findUsers();

        model.addAttribute("users", findUsers);
        model.addAttribute("usersCount", findUsers.size());
        return "user/list";
    }

    @GetMapping("/users/{userNickname}")
    public String getProfile(@PathVariable("userNickname") String userNickname,
                             Model model) {

        Optional<User> findUser = userService.findOne(userNickname);
        logger.info("GET /user/{} : View user({}) profile", userNickname, userNickname);

        model.addAttribute("user", findUser.get());
        return "/user/profile";
    }
}
