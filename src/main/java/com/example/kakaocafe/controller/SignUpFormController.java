package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.ViewPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SignUpFormController {

    @GetMapping(path = "/sign-up")
    public ModelAndView showSignUpForm(HttpServletRequest request) {

        final Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        final ModelAndView modelAndView = new ModelAndView(ViewPath.SIGN_UP);

        if (flashMap != null) {
            modelAndView.addObject("isFailed", flashMap.get("isFailed"))
                    .addObject("name", flashMap.get("name"))
                    .addObject("email", flashMap.get("email"));
        }

        return modelAndView;
    }

    @GetMapping(path = "/sign-up-success")
    public ModelAndView showSignUpSuccess(HttpServletRequest request) {

        final Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        final ModelAndView modelAndView = new ModelAndView(ViewPath.SIGN_UP);

        if (flashMap != null) {
            modelAndView.addObject("name", flashMap.get("name"))
                    .addObject("email", flashMap.get("email"));
        }

        return modelAndView;
    }
}
