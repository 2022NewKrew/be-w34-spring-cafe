package com.kakao.cafe.web.controller.mvc;

import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.common.RequireLogin;
import com.kakao.cafe.web.controller.KakaoCafePageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@KakaoCafePageController
@EnableSession
public class MainPageController {

  private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

  public MainPageController() {

  }


  /**
   * 메인 페이지
   *
   * @param model MVC
   * @return index.html
   */
  @GetMapping("/")
  public String index(Model model) {
    return "index";
  }


  /**
   * 회원 가입 페이지
   *
   * @param model MVC
   * @return form.html
   */
  @RequireLogin(value = false)
  @GetMapping("/signup")
  public String signUp(Model model) {
    return "form";
  }


  /**
   * 로그인 페이지
   *
   * @param model MVC
   * @return login.html
   */
  @RequireLogin(value = false)
  @GetMapping("/login")
  public String login(Model model) {
    return "login";
  }

}
