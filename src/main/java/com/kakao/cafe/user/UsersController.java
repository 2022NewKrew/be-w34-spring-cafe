package com.kakao.cafe.user;

import com.kakao.cafe.user.dto.request.UserRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String viewUsers(Model model) {
        UsersResponse usersResponse = userService.findAll();
        model.addAttribute("users", usersResponse.getUsers());
        return "user/list";
    }

    @PostMapping()
    public String createUser(UserRequest userRequest) {
        userService.save(userRequest);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String viewUser(@PathVariable String userId, Model model) {
        UserResponse userResponse = userService.findById(userId);
        model.addAttribute("user", userResponse);
        return "user/profile";
    }

    @PostMapping("/login")
    public String login(UserRequest userRequest, HttpSession httpSession) {
        UUID sessionId = userService.loginedUserSessionId(userRequest);
        httpSession.setAttribute("sessionId", sessionId);
        return "redirect:/";
    }
}
