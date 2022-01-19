package com.kakao.cafe.web.article;

import com.kakao.cafe.service.article.ArticleCreateService;
import com.kakao.cafe.service.article.ArticleFindService;
import com.kakao.cafe.web.article.dto.ArticleCreateRequest;
import com.kakao.cafe.web.article.dto.ArticleDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleCreateService articleCreateService;
    private final ArticleFindService articleFindService;

    @Autowired
    public ArticleController(ArticleCreateService articleCreateService, ArticleFindService articleFindService) {
        this.articleCreateService = articleCreateService;
        this.articleFindService = articleFindService;
    }
    @GetMapping("/form")
    public String articleCreateForm() {
        return "/article/form";
    }

    @PostMapping("/form")
    public String articleCreate(@ModelAttribute ArticleCreateRequest requestDto) {
        articleCreateService.create(requestDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/{articleId}")
    public String showArticle(@PathVariable int articleId, Model model) {
        model.addAttribute("article", new ArticleDetailResponse(articleFindService.findById(articleId)));

        return "/article/show";
    }

    //TODO: article show에서 목록, 수정, 삭제버튼
}
