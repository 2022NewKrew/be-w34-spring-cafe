package com.kakao.cafe.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CafeDefaultViewController {
    @GetMapping("/")
    String forwardDefaultUrl () {
        return "redirect:/posts/list";
    }
}
