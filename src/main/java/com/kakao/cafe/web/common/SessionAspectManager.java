package com.kakao.cafe.web.common;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.utils.SessionUtils;
import java.util.Optional;
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

  @Pointcut("@within(com.kakao.cafe.web.common.EnableSession)")
  public void enableSession() {
  }

  @Around("enableSession()")
  public Object setSessionAttribute(ProceedingJoinPoint joinPoint) throws Throwable {

    logger.debug("SessionManager Aspect Start-----------------------------------------------");

    boolean isLogin = SessionUtils.isLogin();
    Optional<User> loginUser = SessionUtils.getLoginUser();

    Object controllerResult = joinPoint.proceed();

    for(Object arg : joinPoint.getArgs()) {
      logger.debug("JoinPoint Arg : {}", arg.toString());
      if(arg instanceof Model) {
        Model model = (Model) arg;
        model.addAttribute("session", loginUser.orElse(User.createEmpty()));
        model.addAttribute("isLogin", isLogin);
      }
    }

    logger.debug("SessionManager Aspect End-------------------------------------------------");

    return controllerResult;
  }

}
