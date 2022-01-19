package com.kakao.cafe.utils;

import com.kakao.cafe.domain.User;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtils {

  public static final String LOGIN = "USER_SESSION";


  /**
   * 현재 요청스레드의 request 객체를 가져온다.
   *
   * @return 요청 세션
   */
  public static HttpSession getSession() {
    // thread local 에서 request 추출
    HttpServletRequest request =
        ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

    return request.getSession();
  }


  /**
   * 현재 로그인 상태 여부 반환
   *
   * @return 로그인 여부
   */
  public static boolean isLogin() {
    return getLoginUser().isPresent();
  }


  /**
   * 로그인 유저 반환, 미로그인 상태인 경우의 경우 User 가 없다.
   *
   * @return 로그인 유저
   */
  public static Optional<User> getLoginUser() {
    HttpSession session = getSession();
    return Optional.ofNullable((User) session.getAttribute(LOGIN));
  }


  /**
   * 로그인 요청 세션 값에 유저 정보 속성 추가
   *
   * @param user 로그인 세션 객체
   */
  public static void login(User user) {
    HttpSession session = getSession();
    session.setAttribute(SessionUtils.LOGIN, user);
  }


  /**
   * 로그아웃
   */
  public static void logout() {
    HttpSession session = getSession();
    session.invalidate();
  }


  /**
   * 로그인 유저의 세션 정보를 최신 유저 정보로 갱신
   *
   * @param user 갱신된 유저 정보
   */
  public static void updateUser(User user) {
    HttpSession session = getSession();
    session.setAttribute(SessionUtils.LOGIN, user);
  }


  /**
   * 해당 유저가 현재 로그인 한 유저인지 확인
   *
   * @param user 검증 유저
   * @return 로그인 유저 여부
   */
  public static boolean isLoginUser(User user) {
    return SessionUtils.getLoginUser()
        .stream()
        .anyMatch(loginUser -> loginUser.equals(user));
  }

}
