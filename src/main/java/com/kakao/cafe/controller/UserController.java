package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Resource(name = "userService")
    UserService userService;

    @GetMapping
    ModelAndView users(HttpServletRequest request) {
        List<User> userList = userService.getUserList();
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", userList);
        mv.setViewName("user/list");

        return mv;
    }

    @GetMapping("/form")
    String form(HttpServletRequest request) {

        return "user/form";
    }

    @GetMapping("/create")
    String create(HttpServletRequest request, User user) {
        userService.insertUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    ModelAndView profile(@PathVariable String userId) {
        User user = userService.getUserByUserId(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", user.getName());
        mv.addObject("email", user.getEmail());
        mv.setViewName("user/profile");
        return mv;
    }

}
