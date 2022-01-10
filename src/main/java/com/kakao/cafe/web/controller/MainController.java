package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.service.UserService;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  private static final Logger logger = LoggerFactory.getLogger(MainController.class);
  private final UserService userService;

  public MainController(UserService userService) {
    this.userService = userService;
  }

  private final UserService userService;

  public MainController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/users")
  public String users(Model model) {
    Users users = userService.getAllUsers();
    model.addAttribute("users", users.stream()
        .map(UserDTO::new)
        .collect(Collectors.toList()));
    return "list";
  }

  @GetMapping("/signup")
  public String signUp() {
    return "form";
  }

}
