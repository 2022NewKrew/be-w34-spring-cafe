package com.kakao.cafe.web.controller.mvc;

import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.controller.KakaoCafePageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@KakaoCafePageController
@EnableSession
public class MainPageController {

  private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

  public MainPageController() {

  }

  @GetMapping("/")
  public String index(Model model) {
    return "index";
  }

  @GetMapping("/signup")
  public String signUp(Model model) {
    return "form";
  }

  @GetMapping("/login")
  public String login(Model model) {
    return "login";
  }

}
