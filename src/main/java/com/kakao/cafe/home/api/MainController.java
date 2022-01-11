package com.kakao.cafe.home.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

   @GetMapping
   public String home() {
       return "index";
   }
}
