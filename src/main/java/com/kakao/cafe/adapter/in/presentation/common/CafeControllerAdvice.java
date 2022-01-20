package com.kakao.cafe.adapter.in.presentation.common;

import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import com.kakao.cafe.view.ErrorMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class CafeControllerAdvice {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthenticatedUserException.class)
    public String authenticatedException(Model model, Exception e) {
        String message = ErrorMessage.getErrorMessage(e);
        model.addAttribute("message", message);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String domainExceptionHandler(HttpServletRequest request, RedirectAttributes redirectAttributes, Exception e) {
        String url = request.getRequestURI();
        String message = ErrorMessage.getErrorMessage(e);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:" + url;
    }
}
