package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.ViewPath;
import com.example.kakaocafe.domain.post.PostDAO;
import com.example.kakaocafe.domain.post.dto.PostOfTableRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class HomeController {

    private final PostDAO postDAO;

    @GetMapping
    public ModelAndView home() {
        final List<PostOfTableRow> postOfTableRowList = postDAO.getAllPostOfTableRow();

        return new ModelAndView(ViewPath.INDEX)
                .addObject("posts", postOfTableRowList);
    }
}
