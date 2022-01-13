package com.kakao.cafe.common.aop;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Aspect
@Component
public class LoginAspect {

    @Pointcut(value = "execution(public String view*(..))")
    private void controllerViewPointcut() {
    }

    /**
     * @param joinPoint
     * @param model
     */
    @Before(value = "controllerViewPointcut() && args(..,model)")
    public void beforeController(JoinPoint joinPoint, Model model) {

        log.info("LoginAspect.beforeController");

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest()
                .getSession();

        log.info("this session userLogin = {}", session.getAttribute("loginUser"));

        User user = (User) session.getAttribute("loginUser");

        if (user != null) {
            model.addAttribute("loginUser", user);

            // TODO: 무스타치 템플릿은 화면단에서 if 렌더링이 안된다.. js를 이용해서 조건문 분기하던가 컨트롤로에서 던져줘야함.
            if (user.getRole().equals(UserStatus.ADMIN)) {
                model.addAttribute("admin", true);
            }
        }

    }
}
