package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.dto.UserDTO.Result;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String create(@ModelAttribute UserDTO.Create dto) {
        userService.create(dto);

        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public ModelAndView read(Map<String, Object> model) {
        List<Result> userDTOs = userService.readAll();
        model.put("users", userDTOs);

        return new ModelAndView("user/list", model);
    }
}
