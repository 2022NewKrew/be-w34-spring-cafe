package com.kakao.cafe.interfaces.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public ModelAndView handleError(ModelAndView modelAndView, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            logger.debug("ERROR : {}", statusCode);
        }

        modelAndView.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
