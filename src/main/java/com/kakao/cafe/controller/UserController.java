package com.kakao.cafe.controller;


import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/create")
    public String addUser(User user) {
        logger.info("user = {}", user);
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUsers(Model model){
        model.addAttribute("users", userRepository.getUserList());
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        User user = userRepository.getUserList().get(id);
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        return "user/profile";
    }


}
