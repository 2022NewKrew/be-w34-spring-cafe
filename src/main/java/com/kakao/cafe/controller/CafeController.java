package com.kakao.cafe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CafeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/articles";
    }
}
