package com.kakao.cafe.handler;

import com.kakao.cafe.exceptions.DuplicateUserException;
import com.kakao.cafe.exceptions.UserNotFoundException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW_NAME = "errors/error";
    private final ModelAndView mv = new ModelAndView(ERROR_VIEW_NAME);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView userNotFound(UserNotFoundException exception) {
        Map<String, Object> model = mv.getModel();
        model.put("message", exception.getMessage());
        return mv;
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView duplicateUser(DuplicateUserException exception) {
        Map<String, Object> model = mv.getModel();
        model.put("message", exception.getMessage());
        return mv;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView validationFailed() {
        Map<String, Object> model = mv.getModel();
        // TODO - Custom Exception 따로 만들어서 던지는게 낫지 않을까?
        model.put("message", "Invalid input");
        return mv;
    }
}
