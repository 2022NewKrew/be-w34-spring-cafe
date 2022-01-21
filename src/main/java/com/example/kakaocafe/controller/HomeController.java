package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomeController {

    @GetMapping(path = "/")
    public ModelAndView home(Model model) {

        if (!model.containsAttribute("posts")) {
            return new ModelAndView(URLPath.GET_ALL_POSTS.getPath() + "?page=1");
        }

        return new ModelAndView(ViewPath.INDEX)
                .addAllObjects(model.asMap());
    }
}
