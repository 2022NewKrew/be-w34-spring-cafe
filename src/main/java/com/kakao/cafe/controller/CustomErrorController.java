package com.kakao.cafe.controller;

import com.kakao.cafe.util.Constants;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    private static final String DEFAULT_ERROR_MESSAGE = "서버에서 에러가 발생했습니다. 잠시후 다시 시도해주세요.";
    private static final String PAGE_NOT_FOUND_ERROR_MESSAGE = "없는 페이지 입니다.";

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {
        String message = DEFAULT_ERROR_MESSAGE;
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                message = PAGE_NOT_FOUND_ERROR_MESSAGE;
            }
        }
        model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, message);
        return Constants.ERROR_PAGE_NAME;
    }
}
