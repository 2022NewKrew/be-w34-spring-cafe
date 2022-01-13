package com.kakao.cafe.utils;

import com.kakao.cafe.domain.User;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtils {

  public static final String LOGIN = "USER_SESSION";

  public static HttpSession getSession() {
    // thread local 에서 request 추출
    HttpServletRequest request =
        ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

    return request.getSession();
  }

  public static boolean isLogin() {
    return getLoginUser().isPresent();
  }

  public static Optional<User> getLoginUser() {
    HttpSession session = getSession();
    return Optional.ofNullable((User) session.getAttribute(LOGIN));
  }

  public static void login(User user) {
    HttpSession session = getSession();
    session.setAttribute(SessionUtils.LOGIN, user);
  }

  public static void logout() {
    HttpSession session = getSession();
    session.invalidate();
  }

}
