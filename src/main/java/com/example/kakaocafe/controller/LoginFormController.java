package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.ViewPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(path = "/login")
@RequiredArgsConstructor
public class LoginFormController {

    @GetMapping
    public ModelAndView showLoginForm(HttpServletRequest request) {

        final ModelAndView modelAndView = new ModelAndView(ViewPath.LOGIN);

        final Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addObject("isFailed", flashMap.get("isFailed"));
        }

        return modelAndView;
    }
}
