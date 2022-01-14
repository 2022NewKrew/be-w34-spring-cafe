package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.view.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArticleInfoController {

    private static final String VIEWS_ARTICLE_LIST = "index";
    private static final String VIEWS_ARTICLE_DETAIL = "article/show";

    private static final Logger log = LoggerFactory.getLogger(ArticleInfoController.class);

    GetArticleInfoUseCase getArticleInfoUseCase;

    public ArticleInfoController(GetArticleInfoUseCase getArticleInfoUseCase) {
        this.getArticleInfoUseCase = getArticleInfoUseCase;
    }

    @GetMapping("/")
    public String displayArticleList(Model model) {
        model.addAttribute(
            "articles",
            getArticleInfoUseCase.getListOfAllArticles().getArticleList()
        );
        return VIEWS_ARTICLE_LIST;
    }

    @GetMapping("/articles/{id}")
    public String displayArticleDetail(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {
        try {
            Article article = getArticleInfoUseCase.getArticleDetail(id);
            log.info("{} is opened", article.getTitle());
            model.addAttribute("article", article);
            return VIEWS_ARTICLE_DETAIL;
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            String message = ErrorMessage.getErrorMessage(e);
            redirectAttributes.addAttribute("message", message);
            return "redirect:/errors";
        }
    }
}
