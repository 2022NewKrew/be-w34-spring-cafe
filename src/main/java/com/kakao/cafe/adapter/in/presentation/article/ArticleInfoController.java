package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.reply.dto.ReplyList;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
            articleList.getArticleList()
        );
        return VIEWS_ARTICLE_LIST;
    }

    @GetMapping("/articles/{id}")
    public String displayArticleDetail(@PathVariable int id, Model model)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        Article article = getArticleInfoUseCase.getArticleDetail(id);
        ReplyList replyList = getRepliesUseCase.getListOfRepliesOfTheArticle(id);
        int countOfReplies = replyList.getReplyList().size();
        model.addAttribute("article", article);
        model.addAttribute("countOfReplies", countOfReplies);
        model.addAttribute(
            "replies",
            replyList.getReplyList()
        );
        return VIEWS_ARTICLE_DETAIL;
    }

    @GetMapping("/articles/{id}/form")
    public String displayArticleModifyForm(@PathVariable int id, Model model)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        Article article = getArticleInfoUseCase.getArticleDetail(id);
        model.addAttribute("article", article);
        return VIEWS_ARTICLE_MODIFY_FORM;
    }
}
