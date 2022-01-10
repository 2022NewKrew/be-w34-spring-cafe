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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String create(@ModelAttribute UserDTO.Create dto) {
        userService.create(dto);

        return "redirect:/users/list";
    }

    @GetMapping("/list")
    public ModelAndView readAll(Map<String, Object> model) {
        List<Result> userDTOs = userService.readAll();
        model.put("users", userDTOs);

        return new ModelAndView("user/list", model);
    }

    @GetMapping("/{userId}")
    public ModelAndView read(Map<String, Object> model,
        @PathVariable String userId) {
        UserDTO.Result userDTO = userService.readByUserId(userId);
        model.put("user", userDTO);

        return new ModelAndView("user/profile", model);
    }
}
