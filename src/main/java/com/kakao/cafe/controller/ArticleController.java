package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Articles;
import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.util.AuthUtils;
import com.kakao.cafe.util.Constant;
import com.kakao.cafe.util.ErrorMessage;
import com.kakao.cafe.util.PageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;

    @PostMapping("/questions")
    public String createQNA(Article article) {
        log.info(article.toString());
        articleService.join(article);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/list?page=1";
    }

    @GetMapping("/list")
    public String index(int page, Model model) {
        log.info("{}page list", page);
        Articles articles = new Articles();
        int numOfArticles = articleService.numOfArticles();
        articles.setTotalCount(numOfArticles);
        articles.setPageList(PageUtils.makePageList(numOfArticles));
        articles.setHasPrev(page != 1);
        articles.setHasNext(page != ((numOfArticles - 1) / 10) + 1);
        articles.setPrev(page - 1);
        articles.setNext(page + 1);

        articles.setArticleList(articleService.findSubList(page));

        model.addAttribute("posts", articles);
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String show(@PathVariable Long index, Model model) {
        Article article = articleService.findOne(index);
        List<Reply> replies = replyService.findReplyList(index);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("writer", article.getWriter());
        model.addAttribute("content", article.getContent());
        model.addAttribute("replies", replies);

        return "" +
                "qna/show";
    }

    @GetMapping("/qna/form")
    public String writeArticle(HttpSession session) {
        User user = (User) session.getAttribute(Constant.LOGIN_SESSION);
        if (user == null) {
            return "redirect:/users/login";
        }
        return "qna/form";
    }

    @PostMapping("/qna/update/{index}")
    public String updateForm(@PathVariable Long index, Model model, HttpSession session) {
        Long userId = AuthUtils.checkLogin(session);
        Article article = articleService.findOne(index);
        articleService.checkWriterByLoginUserid(article, userId);

        model.addAttribute("index", index);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getContent());
        return "qna/updateForm";
    }

    @PutMapping("/qna/updateArticle/{index}")
    public String updateArticle(@PathVariable Long index, Article article, HttpSession session) {
        Long userId = AuthUtils.checkLogin(session);

        article.setIndex(index);
        article.setWriterId(userId);
        articleService.checkWriterByLoginUserid(article, userId);
        articleService.updateArticle(article);
        return "redirect:/articles/" + index;
    }

    @DeleteMapping("/qna/deleteArticle/{index}")
    public String deleteArticle(@PathVariable Long index, HttpSession session) {
        Long userId = AuthUtils.checkLogin(session);
        Article article = articleService.findOne(index);
        articleService.checkWriterByLoginUserid(article, userId);

        checkArticleReplies(index, article.getWriterId());
        articleService.deleteArticle(index);
        replyService.deleteAllRepliesOnArticle(index);
        return "redirect:/";
    }

    private void checkArticleReplies(Long index, Long writerId) {
        List<Reply> replies = replyService.findReplyList(index);
        for (Reply reply : replies) {
            if (!reply.getWriterId().equals(writerId)) {
                throw new IllegalStateException(ErrorMessage.ARTICLE_DELETE_NOT_MY_REPLY.getMsg());
            }
        }
    }
}
