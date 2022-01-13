package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleCreateDto;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleDetailService articleDetailService;

    private final ArticleCreateService articleCreateService;

    public ArticleController(ArticleDetailService articleDetailService, ArticleCreateService articleCreateService) {
        this.articleDetailService = articleDetailService;
        this.articleCreateService = articleCreateService;
    }

    @GetMapping("article/{id}")
    public String showArticleDetail(Model model,
                                    @PathVariable("id") Long id) {
        ArticleResponseDto article = articleDetailService.showArticleDetail(id);
        model.addAttribute("article", article);
        return "/article/show";
    }

    @GetMapping("article/create")
    public String initCreateForm() {
        return "/article/createForm";
    }

    @PostMapping("article/create")
    public String ProceedCreateForm(ArticleCreateDto dto) {
        dto.setAuthor("mino.lee");
        articleCreateService.createArticle(dto);
        return "redirect:/";
    }
}
