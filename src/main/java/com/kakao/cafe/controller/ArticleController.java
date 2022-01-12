package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final UserService userService = new UserService();

    @PostMapping("/posts")
    public String addUser(@ModelAttribute UserDto userDto) {
        logger.info("POST /user {}", userDto);
        userService.addUser(userDto);
        return "redirect:/users";
    }


//    @GetMapping("/users/{id}")
//    public String findById(@PathVariable int id , Model model) {
//        logger.info("GET /users/{}", id);
//        model.addAttribute("user",userService.findById(id));
//        return "user/profile";
//    }
}
