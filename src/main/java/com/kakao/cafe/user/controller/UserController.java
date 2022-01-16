package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.User.UserNoPassword;
import com.kakao.cafe.user.service.UserService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    public static final String ERROR_MSG = "error";
    public static final String ERROR_STATUS = "status";
    public static final String REDIRECT_ERROR_NO_PAGE = "redirect:/error/no-page";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String postSignup(String userId, String password, String name, String email, RedirectAttributes re) {
        try {
            final User user = new User(userId, password, name, email);
            userService.signup(user);
            LOGGER.info("POST request on Signup -> {}", user);
            return "redirect:users";
        } catch (IllegalArgumentException e) {
            LOGGER.warn(e.getMessage());
            re.addFlashAttribute(ERROR_MSG, e.getMessage());
            return REDIRECT_ERROR_NO_PAGE;
        }
    }

    @GetMapping()
    public String getUserList(Model model) {
        final Collection<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserProfile(@PathVariable("userId") String userId, Model model, RedirectAttributes re) {
        try {
            final User user = userService.getUserByUserId(userId);
            final UserNoPassword userNoPassword = user.userNoPassword();
            model.addAttribute("user", userNoPassword);
            return "user/profile";
        } catch (ResponseStatusException e) {
            LOGGER.warn(e.getMessage());
            re.addFlashAttribute(ERROR_STATUS, e.getStatus());
            re.addFlashAttribute(ERROR_MSG, e.getReason());
            return REDIRECT_ERROR_NO_PAGE;
        }
    }

    @GetMapping("/{userId}/form")
    public String getUserUpdate(@PathVariable("userId") String userId, Model model, RedirectAttributes re) {
        try {
            final User user = userService.getUserByUserId(userId);
            model.addAttribute("user", user);
            return "user/updateForm";
        } catch (ResponseStatusException e) {
            LOGGER.warn(e.getMessage());
            re.addFlashAttribute(ERROR_STATUS, e.getStatus());
            re.addFlashAttribute(ERROR_MSG, e.getReason());
            return REDIRECT_ERROR_NO_PAGE;
        }
    }

    @PostMapping("/{userId}/form")
    public String postUserUpdate(@PathVariable("userId") String userId, String password, String name, String email, RedirectAttributes re) {
        try {
            final User user = userService.updateUser(userId, password, name, email);
            LOGGER.info("POST request on UpdateUser -> {}", user);
            return "redirect:/users/" + userId;
        } catch (ResponseStatusException e) {
            LOGGER.warn(e.getMessage());
            re.addFlashAttribute(ERROR_STATUS, e.getStatus());
            re.addFlashAttribute(ERROR_MSG, e.getReason());
        } catch (IllegalArgumentException e) {
            LOGGER.warn(e.getMessage());
            re.addFlashAttribute(ERROR_MSG, e.getMessage());
        }
        return REDIRECT_ERROR_NO_PAGE;
    }
}
