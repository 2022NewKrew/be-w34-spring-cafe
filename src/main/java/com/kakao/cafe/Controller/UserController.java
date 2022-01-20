package com.kakao.cafe.Controller;

import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String join(User user, Model model) {
        logger.info("POST /users", user.getNickName());
        try {
            userService.join(user);
            logger.info("{}(nickname) joined.", user.getNickName());
            return "redirect:/joined/" + user.getId();
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }

    @GetMapping("/joined/{userId}")
    public String joinSuccess(@PathVariable("userId") Long userId,
                              Model model) {
        logger.info("GET /joined/{}", userId);
        Optional<User> findUser = userService.findOneById(userId);

        model.addAttribute("user", findUser.get());
        return "user/join_success";
    }

    @GetMapping("/users")
    public String getList(Model model) {
        logger.info("GET /users: Get all users.");
        List<User> findUsers = userService.findUsers();

        model.addAttribute("users", findUsers);
        model.addAttribute("usersCount", findUsers.size());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(@PathVariable("userId") Long userId,
                             Model model) {
        logger.info("GET /user/{} : View user({}) profile", userId, userId);
        Optional<User> findUser = userService.findOneById(userId);

        model.addAttribute("user", findUser.get());
        return "user/profile";
    }

    @PostMapping("login")
    public String login(String email, String password, HttpSession session, Model model) throws IllegalAccessException {
        logger.info("POST /login");
        try {
            User loginUser = userService.login(email, password);
            logger.info("{} logged in", email);
            session.setAttribute("sessionedUser", loginUser);
            return "redirect:/";
        } catch (IllegalAccessException e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }

    @GetMapping("/edit-user")
    public String editUser(HttpSession session, Model model) throws IllegalAccessException {
        logger.info("GET /edit-user");
        try {
            Object sessionedUser = session.getAttribute("sessionedUser");
            User user = userService.getLoggedInUser(sessionedUser);
            model.addAttribute(user);

            return "user/edit_profile";
        } catch (IllegalAccessException e) {
            model.addAttribute("error", e.getMessage());
            return "error/page";
        }
    }

    @PostMapping("/edit-user/{userId}")
    public String editPostUser(@PathVariable("userId") Long userId,
                               String newEmail, String newNickName) {
        logger.info("POST /edit-user/{}", userId);
        userService.editUserInfo(userId, newEmail, newNickName);
        logger.info("User Id({}) changed user information to {} - {}", userId, newNickName, newEmail);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("GET /logout : Logout from current user");
        session.invalidate();
        return "redirect:/";
    }
}
