package com.kakao.cafe.web.user;

import com.kakao.cafe.domain.user.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler {
    Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

    //TODO: ajax로 변경해서 실시간 처리하도록 하자
//    @ExceptionHandler({IllegalUserIdFormatException.class, IllegalUserIdLengthException.class, IllegalPasswordFormatException.class, IllegalNameFormatException.class, IllegalEmailFormatException.class})


    @ExceptionHandler({IllegalUserIdFormatException.class, IllegalUserIdLengthException.class})
    public ModelAndView userIdException(HttpServletRequest request, IllegalUserIdException e) {
        logger.error("ERROR : " + e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userIdError",e.getMessage());
        modelAndView.addObject("password", request.getParameter("password"));
        modelAndView.addObject("name", request.getParameter("name"));
        modelAndView.addObject("email", request.getParameter("email"));
        modelAndView.setViewName("/user/form");

        return modelAndView;
    }


    @ExceptionHandler(IllegalPasswordFormatException.class)
    public ModelAndView passwordException(HttpServletRequest request, IllegalPasswordFormatException e) {
        logger.error("ERROR : " + e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passwordError",e.getMessage());
        modelAndView.addObject("userId", request.getParameter("userId"));
        modelAndView.addObject("name", request.getParameter("name"));
        modelAndView.addObject("email", request.getParameter("email"));
        modelAndView.setViewName("/user/form");

        return modelAndView;
    }

    @ExceptionHandler(IllegalNameFormatException.class)
    public ModelAndView nameException(HttpServletRequest request, IllegalNameFormatException e) {
        logger.error("ERROR : " + e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nameError",e.getMessage());
        modelAndView.addObject("userId", request.getParameter("userId"));
        modelAndView.addObject("password", request.getParameter("password"));
        modelAndView.addObject("email", request.getParameter("email"));
        modelAndView.setViewName("/user/form");

        return modelAndView;
    }

    @ExceptionHandler(IllegalEmailFormatException.class)
    public ModelAndView emailException(HttpServletRequest request, IllegalEmailFormatException e) {
        logger.error("ERROR : " + e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("emailError",e.getMessage());
        modelAndView.addObject("userId", request.getParameter("userId"));
        modelAndView.addObject("password", request.getParameter("password"));
        modelAndView.addObject("name", request.getParameter("name"));
        modelAndView.setViewName("/user/form");

        return modelAndView;
    }

}
