package com.kakao.cafe.web.controller.mvc;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.exception.NoAuthorityException;
import com.kakao.cafe.utils.SessionUtils;
import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.common.RequireLogin;
import com.kakao.cafe.web.controller.KakaoCafePageController;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.service.UserService;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@KakaoCafePageController
@EnableSession
public class UserPageController {

  private static final Logger logger = LoggerFactory.getLogger(UserPageController.class);
  private final UserService userService;

  public UserPageController(UserService userService) {
    this.userService = userService;
  }


  /**
   * 멤버리스트 페이지
   *
   * @param model MVC
   * @return list.html
   */
  @GetMapping("/users")
  public String users(Model model) {

    Users users = userService.findAllUsers();

    model.addAttribute("users", users.stream()
        .map(UserDTO::new)
        .collect(Collectors.toList()));

    return "list";
  }


  /**
   * 멤버 상세 페이지
   *
   * @param model MVC
   * @param id    유저 id
   * @return profile.html
   */
  @GetMapping("/users/{id}")
  public String users(Model model, @PathVariable("id") Long id) {

    User user = userService.findUserById(id);
    boolean isLoginUser = SessionUtils.isLoginUser(user);

    model.addAttribute("user", new UserDTO(user));
    model.addAttribute("isLoginUser", isLoginUser);

    return "profile";
  }


  /**
   * 멤버 변경 페이지, 수정 권한 필요
   *
   * @param model MVC
   * @param id    유저 id
   * @return profile_modify.html
   * @throws NoAuthorityException 수정 권한 없음
   */
  @RequireLogin
  @GetMapping("/users/{id}/modify")
  public String modifyUser(Model model, @PathVariable("id") Long id) {

    User user = userService.findUserById(id);

    if (!userService.hasEditPermission(user)) {
      throw new NoAuthorityException();
    }

    model.addAttribute("user", new UserDTO(user));

    return "profile_modify";
  }

}
