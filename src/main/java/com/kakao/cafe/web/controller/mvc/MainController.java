package com.kakao.cafe.web.controller.mvc;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.service.UserService;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@EnableSession
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
  public String index(Model model) {
    return "index";
  }

  @GetMapping("/users")
  public String users(Model model) {

    Users users = userService.findAllUsers();

    model.addAttribute("users", users.stream()
        .map(UserDTO::new)
        .collect(Collectors.toList()));

    return "list";
  }

  @GetMapping("/users/{email}")
  public String users(Model model, @PathVariable("email") String email) {

    User user = userService.findUserByEmail(email);
    boolean isLoginUser = userService.isLoginUser(user);

    model.addAttribute("user", new UserDTO(user));
    model.addAttribute("isLoginUser", isLoginUser);

    return "profile";
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
