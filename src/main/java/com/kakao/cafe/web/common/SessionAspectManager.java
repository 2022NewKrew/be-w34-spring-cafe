package com.kakao.cafe.web.common;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.utils.SessionUtils;
import com.kakao.cafe.web.dto.UserDTO;
import java.util.Arrays;
import java.util.Optional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class SessionAspectManager {

  private static final Logger logger = LoggerFactory.getLogger(SessionAspectManager.class);

  @Pointcut("within(@com.kakao.cafe.web.common.EnableSession *)")
  public void enableSession() {
  }


  @Around("enableSession()")
  public Object setSessionAttribute(ProceedingJoinPoint joinPoint) throws Throwable {

    logger.debug("enableSession Aspect Start-----------------------------------------------");

    Object controllerResult = joinPoint.proceed();

    findModel(joinPoint).ifPresent(model -> {
      boolean isLogin = SessionUtils.isLogin();
      Optional<User> loginUser = SessionUtils.getLoginUser();
      model.addAttribute("session", new UserDTO(loginUser.orElseGet(User::createEmpty)));
      model.addAttribute("isLogin", isLogin);
    });

    logger.debug("enableSession Aspect End-------------------------------------------------");

    return controllerResult;
  }


  private Optional<Model> findModel(JoinPoint joinPoint) {
    return Arrays.stream(joinPoint.getArgs())
        .filter(arg -> arg instanceof Model)
        .map(arg -> (Model) arg)
        .findAny();
  }


  @Around(value = "@annotation(requireLogin)")
  public Object checkLogin(ProceedingJoinPoint joinPoint, RequireLogin requireLogin) throws Throwable {

    logger.debug("requireLogin Aspect Start-----------------------------------------------");

    Object controllerResult = requireLogin.value() ? "/login" : "/";
    if (matchRequireLogin(requireLogin)) {
      controllerResult = joinPoint.proceed();
    }

    logger.debug("requireLogin Aspect End-------------------------------------------------");

    return controllerResult;
  }


  private boolean matchRequireLogin(RequireLogin requireLogin) {
    if(requireLogin.value()) {
      return SessionUtils.isLogin();
    }
    return !SessionUtils.isLogin();
  }

}
