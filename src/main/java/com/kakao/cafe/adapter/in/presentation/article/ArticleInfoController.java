package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.reply.dto.ReplyList;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
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

@Controller
public class ArticleInfoController {

    private static final String VIEWS_ARTICLE_LIST = "index";
    private static final String VIEWS_ARTICLE_DETAIL = "article/show";
    private static final String VIEWS_ARTICLE_MODIFY_FORM = "article/modifyForm";

    private final GetArticleInfoUseCase getArticleInfoUseCase;
    private final GetRepliesUseCase getRepliesUseCase;

    public ArticleInfoController(GetArticleInfoUseCase getArticleInfoUseCase, GetRepliesUseCase getRepliesUseCase) {
        this.getArticleInfoUseCase = getArticleInfoUseCase;
        this.getRepliesUseCase = getRepliesUseCase;
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
    public String displayArticleDetail(@PathVariable int id, Model model, HttpSession session)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException, UnauthenticatedUserException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        Article article = getArticleInfoUseCase.getArticleDetail(id, sessionedUser.getUserId(), sessionedUser);
        ReplyList replyList = getRepliesUseCase.getListOfRepliesOfTheArticle(id);
        int countOfReplies = replyList.getValue().size();
        model.addAttribute("article", article);
        model.addAttribute("countOfReplies", countOfReplies);
        model.addAttribute(
            "replies",
            replyList.getValue()
        );
        return VIEWS_ARTICLE_DETAIL;
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
