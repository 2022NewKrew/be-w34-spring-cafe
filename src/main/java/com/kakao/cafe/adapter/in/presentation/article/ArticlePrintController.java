package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArticlePrintController {

    private static final String VIEWS_ARTICLE_LIST = "index";
    private static final String VIEWS_ARTICLE_MODIFY_FORM = "article/modifyForm";

    private final GetArticleInfoUseCase getArticleInfoUseCase;

    public ArticlePrintController(GetArticleInfoUseCase getArticleInfoUseCase) {
        this.getArticleInfoUseCase = getArticleInfoUseCase;
    }

    @GetMapping("/")
    public String displayArticleList(Model model) {
        ArticleList articleList = getArticleInfoUseCase.getListOfAllArticles();
        model.addAttribute(
            "articles",
            articleList.getValue()
        );
        return VIEWS_ARTICLE_LIST;
    }

    @GetMapping("/articles/{id}")
    public String displayArticleDetail(@PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException, UnauthenticatedUserException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        Article article = getArticleInfoUseCase.getArticleDetail(id, sessionedUser.getUserId(), sessionedUser);
        redirectAttributes.addFlashAttribute("article", article);

        return "redirect:/articles/" + id + "/replies";
    }

    @GetMapping("/articles/{id}/form")
    public String displayArticleModifyForm(@PathVariable int id, @RequestParam String userId, Model model, HttpSession session)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException, UnauthenticatedUserException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        Article article = getArticleInfoUseCase.getArticleDetail(id, userId, sessionedUser);
        model.addAttribute("article", article);
        return VIEWS_ARTICLE_MODIFY_FORM;
    }
}
