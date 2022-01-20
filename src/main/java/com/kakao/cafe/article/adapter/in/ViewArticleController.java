package com.kakao.cafe.article.adapter.in;

import com.kakao.cafe.article.application.port.in.FindArticleUseCase;
import com.kakao.cafe.article.application.port.in.FoundArticleDto;
import com.kakao.cafe.article.domain.ArticleId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ViewArticleController {

    private final FindArticleUseCase findArticleUseCase;

    public ViewArticleController(FindArticleUseCase findArticleUseCase) {
        this.findArticleUseCase = findArticleUseCase;
    }
    
    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable int articleId, Model model) {
        FoundArticleDto foundArticle = findArticleUseCase.find(new ArticleId(articleId));
        model.addAttribute("article", foundArticle);
        return "/article/show";
    }
}
