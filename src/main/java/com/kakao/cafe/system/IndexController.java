package com.kakao.cafe.system;

import com.kakao.cafe.qna.article.ArticleViewDto;
import com.kakao.cafe.qna.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 3:18
 */
@Controller
public class IndexController {

    private final ArticleService articleService;

    @Autowired
    public IndexController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<ArticleViewDto> articleViewDtos = articleService.findAll()
                        .stream()
                        .map(ArticleViewDto::new)
                        .collect(Collectors.toList());
        model.addAttribute("articles", articleViewDtos);
        return "index";
    }
}
