package com.kakao.cafe.adapter.in.view.article;

import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleInfoController {

    private static final String VIEWS_ARTICLE_LIST = "index";
    private static final String VIEWS_ARTICLE_DETAIL = "article/show";

    private static final Logger log = LoggerFactory.getLogger(ArticleInfoController.class);

    GetArticleInfoUseCase getArticleInfoUseCase;

    public ArticleInfoController(
        GetArticleInfoUseCase getArticleInfoUseCase) {
        this.getArticleInfoUseCase = getArticleInfoUseCase;
    }

    @GetMapping("/")
    public String displayArticleList(Model model) {
        model.addAttribute("articles",
            getArticleInfoUseCase.getListOfAllArticles().getArticleList());
        return VIEWS_ARTICLE_LIST;
    }

    @GetMapping("/articles/{index}")
    public String displayArticleDetail(@PathVariable int index, Model model) {
        ArticleDetail articleDetail = getArticleInfoUseCase.getArticleDetail(index);
        if (articleDetail.getIndex() == index) {
            log.info("{} is opened", articleDetail.getTitle());
            model.addAttribute("article", articleDetail);
            return VIEWS_ARTICLE_DETAIL;
        }
        log.warn("{} is not in Article List", articleDetail.getTitle());
        return "redirect:/";
    }
}
