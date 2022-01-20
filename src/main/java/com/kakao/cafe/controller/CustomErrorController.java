package com.kakao.cafe.controller;

import com.kakao.cafe.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {
        String message = Constants.DEFAULT_ERROR_MESSAGE;
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                message = Constants.PAGE_NOT_FOUND_ERROR_MESSAGE;
            }
        }
        log.info(message);
        model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, message);
        return Constants.ERROR_PAGE_NAME;
    }
}
