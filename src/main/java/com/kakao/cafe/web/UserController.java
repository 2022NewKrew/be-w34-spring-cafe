package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;

import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserRepository userRepository = new UserRepository();

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.getAllUser());
        return "user/list";
    }

    @PostMapping("/users")
    public String addUser(User user) {
        userRepository.store(user);
        return "redirect:/users/";
    }

    @GetMapping("/users/{userId}")
    public String printProfile(@PathVariable String userId, Model model) {
        User target = userRepository.search(userId);
        model.addAttribute("name", target.getName())
                .addAttribute("email", target.getEmail());
        return "user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model) {
        User target = userRepository.search(userId);
        model.addAttribute("userId", target.getUserId())
                .addAttribute("password", target.getPassword())
                .addAttribute("name", target.getName())
                .addAttribute("email", target.getEmail());
        return "user/updateForm";
    }

    @PostMapping("users/{userId}/update")
    public String updateUser(@PathVariable String userId, User user) {
        int index = userRepository.findByName(userId);
        userRepository.modify(index, user);
        return "redirect:/users/";
    }
}
