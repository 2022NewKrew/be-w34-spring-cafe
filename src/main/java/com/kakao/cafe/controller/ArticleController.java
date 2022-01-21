package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PageNumberDto;
import com.kakao.cafe.exception.IncorrectUserException;
import com.kakao.cafe.exception.NotLoginException;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.service.SessionService;
import com.kakao.cafe.util.ButtonUtil;
import com.kakao.cafe.util.ErrorUtil;
import com.kakao.cafe.vo.Article;
import com.kakao.cafe.vo.Reply;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final SessionService sessionService;
    private final ReplyService replyService;

    public ArticleController(ArticleService articleService, SessionService sessionService, ReplyService replyService) {
        this.articleService = articleService;
        this.sessionService = sessionService;
        this.replyService = replyService;
    }

    @PostMapping("/article/create")
    public String questionForm(Article article, HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);
        articleService.addArticle(article, loginUser);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String getDetails(@PathVariable int index, Model model, HttpSession session) throws NotLoginException {
        sessionService.getLoginUser(session);

        Article article = articleService.getArticle(index);
        List<Reply> replys = replyService.getReplies(index);
        model.addAttribute("article", article);
        model.addAttribute("index", index);
        model.addAttribute("replys", replys);
        return "/qna/show";
    }

    @GetMapping("/")
    public String getArticles(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "redirect:/page/1/0";
    }

    @GetMapping("/questions/form")
    public String getQuestionForm(Model model, HttpSession session) throws NotLoginException {
        User loginUser = sessionService.getLoginUser(session);

        model.addAttribute("userId", loginUser.getUserId());
        return "/qna/form";
    }

    @GetMapping("/questions/{index}/edit")
    public String updateForm(@PathVariable int index, Model model, HttpSession session) throws Exception{
        User loginUser = sessionService.getLoginUser(session);
        Article article = articleService.getArticle(index);
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), article.getWriter()))
            throw new IncorrectUserException();
        model.addAttribute("article", article);
        model.addAttribute("index", index);
        return "/qna/updateForm";
    }

    @PutMapping("/article/{index}/edit")
    public String editArticle(@PathVariable int index, Article article, HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);
        articleService.updateArticle(index, article, loginUser);
        return "redirect:/";
    }

    @DeleteMapping("/questions/{index}")
    public String deleteAriticle(@PathVariable int index, HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);
        articleService.deleteArticle(index, loginUser);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNumber}/{pageIndex}")
    public String getPage(@PathVariable int pageNumber, @PathVariable int pageIndex, Model model) {
        List<Article> articles = articleService.getArticles();
        List<Article> subarticles = articleService.getSubArticles(articles, pageNumber);
        List<PageNumberDto> pageNumbers = articleService.getPageNumbers(articles, pageIndex);
        model.addAttribute("articles", subarticles);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("hasLeftButton", ButtonUtil.hasLeftButton(pageIndex));
        model.addAttribute("hasRightButton", ButtonUtil.hasRightButton(articles, pageIndex));
        model.addAttribute("thisPageNumber", pageNumber);
        model.addAttribute("pageIndex", pageIndex);
        return "index";
    }

    @GetMapping("/page/left/{pageNumber}/{pageIndex}")
    public String getLeftPage(@PathVariable int pageNumber, @PathVariable int pageIndex) {
        return String.format("/page/%d/%d", pageNumber, pageIndex - 1);
    }

    @GetMapping("/page/right/{pageNumber}/{pageIndex}")
    public String getRightPage(@PathVariable int pageNumber, @PathVariable int pageIndex) {
        return String.format("/page/%d/%d", pageNumber, pageIndex + 1);
    }

    @GetMapping("/test/page")
    public String testPage(HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);
        Article article = new Article("picosw", "test", "tests");
        for(int i = 0; i < 15 * 17; i++)
            articleService.addArticle(article, loginUser);
        return "redirect:/";
    }





}
