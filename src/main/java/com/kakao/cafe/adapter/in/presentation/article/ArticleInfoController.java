package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import javax.servlet.http.HttpServletRequest;
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

    public ArticleInfoController(GetArticleInfoUseCase getArticleInfoUseCase) {
        this.getArticleInfoUseCase = getArticleInfoUseCase;
    }

    @GetMapping("/")
    public String displayArticleList(HttpServletRequest request, Model model) {
        log.info("[{}]Article list required", request.getRequestURI());
        model.addAttribute(
            "articles",
            getArticleInfoUseCase.getListOfAllArticles().getArticleList()
        );
        log.info("[{}]Success show article list", request.getRequestURI());
        return VIEWS_ARTICLE_LIST;
    }

    @GetMapping("/articles/{id}")
    public String displayArticleDetail(HttpServletRequest request, @PathVariable int id, Model model)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        log.info("[{}]Article {} is required opening", request.getRequestURI(), id);
        Article article = getArticleInfoUseCase.getArticleDetail(id);
        log.info("[{}]Success {} is opened", request.getRequestURI(), article.getTitle());
        model.addAttribute("article", article);
        return VIEWS_ARTICLE_DETAIL;
    }
}
