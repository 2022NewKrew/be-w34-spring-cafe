package com.kakao.cafe.user;

import com.kakao.cafe.user.dto.request.UserRequest;
import com.kakao.cafe.user.dto.response.UsersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final Logger logger = LoggerFactory.getLogger(UsersController.class);

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        logger.info("유저목록");
        UsersResponse usersResponse = userService.findAll();
        model.addAttribute("users", usersResponse.getUsers());
        return "user/list";
    }

    @PostMapping(value = "/users")
    public String createUser(UserRequest userRequest) {
        userService.save(userRequest);
        return "redirect:/users";
    }
}
