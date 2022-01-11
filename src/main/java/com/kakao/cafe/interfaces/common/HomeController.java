package com.kakao.cafe.interfaces.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String getMainPage() {
        return "index";
    }
}
