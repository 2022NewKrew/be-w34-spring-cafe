package com.kakao.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    public String userRoot(Model model) {
        Map attributes = new HashMap<>();
        attributes.put("test", "hello Mustache");
        attributes.put("articles", List.of(Map.of("num","1", "title", "제목1", "author","작성자이름1"), Map.of("num","2","title", "제목2", "author","작성자이름2")));
        model.addAllAttributes(attributes);

        return "index";
    }

}
