package com.kakao.cafe;

import com.kakao.cafe.article.application.port.in.FindArticleUseCase;
import com.kakao.cafe.article.application.port.in.FoundArticleDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    private final FindArticleUseCase findArticleUseCase;

    public RootController(FindArticleUseCase findArticleUseCase) {
        this.findArticleUseCase = findArticleUseCase;
    }

    @GetMapping()
    public String showRoot(Model model) {
        List<FoundArticleDto> foundArticles = this.findArticleUseCase.findAll();
        model.addAttribute("articles", foundArticles);
        return "/index";
    }
}
