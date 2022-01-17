package com.kakao.cafe.adapter.in.presentation;

import com.kakao.cafe.adapter.in.presentation.user.UserInfoController;
import com.kakao.cafe.adapter.in.presentation.user.UserSignUpController;
import com.kakao.cafe.adapter.in.presentation.user.UserUpdateController;
import com.kakao.cafe.view.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {
    UserInfoController.class, UserSignUpController.class, UserUpdateController.class
})
public class CafeControllerAdvice {

    private static final String VIEW_ERROR_PAGE = "error";

    private static final Logger log = LoggerFactory.getLogger(CafeControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public String cafeExceptionHandler(Model model, Exception e) {
        log.info("{}", e.getMessage());
        String message = ErrorMessage.getErrorMessage(e);
        model.addAttribute("message", message);
        return VIEW_ERROR_PAGE;
    }
}
