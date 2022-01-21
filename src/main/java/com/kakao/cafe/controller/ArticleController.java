package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public ArticleController(ArticleService articleService, ReplyService replyService) {
        this.articleService = articleService;
        this.replyService = replyService;
    }

    @PostMapping("/qna/create")
    public String createArticle(ArticleRequestDto articleRequestDto, HttpSession httpSession) {
        articleService.createArticle(articleRequestDto, httpSession);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getArticleList(Model model) {
        List<ArticleListDto> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    @GetMapping("/qna/form")
    public String createArticle(HttpSession httpSession){
        User sessionedUser = (User) httpSession.getAttribute("sessionedUser");
        if(sessionedUser == null)
            return "redirect:/user/login";
        return "qna/form";
    }

    @GetMapping("/articles/{index}")
    public String getArticleInfo(@PathVariable String index, Model model, HttpSession httpSession) {
        ArticleDto article = articleService.findById(index);
        if (article == null) {
            return "error/articleNotExist";
        }
        model.addAttribute("article", article);
        model.addAttribute("writerName", article.getWriter().getName());

        List<ReplyDto> replyList = replyService.getReplyList(index);
        model.addAttribute("replyList", replyList);
        model.addAttribute("numOfReply", replyList.size());

        User currentUser = (User) httpSession.getAttribute("sessionedUser");
        if (currentUser == null)
            model.addAttribute("currentUser", "로그인 필요");
        else model.addAttribute("currentUser", currentUser.getName());

        return "qna/show";
    }

    @GetMapping("/articles/update/{index}")
    public String getArticleUpdateForm(@PathVariable String index, Model model, HttpSession httpSession) {
        ArticleDto article = articleService.findById(index);
        if (article == null) {
            return "error/articleNotExist";
        }
        model.addAttribute("article", article);

        if(!articleService.isUpdatable(article, httpSession))
            return "qna/updateImpossible";

        return "qna/update";
    }

    @PutMapping("/qna/update/{index}")
    public String updateArticle(@PathVariable String index, ArticleRequestDto articleRequestDto, Model model) {
        ArticleDto article = articleService.updateById(index, articleRequestDto);
        model.addAttribute("article", article);
        return "redirect:/articles/{index}";
    }

    @DeleteMapping("/qna/delete/{index}")
    public String deleteArticle(@PathVariable String index, HttpSession httpSession) {
        ArticleDto article = articleService.findById(index);
        if (article == null) {
            return "error/articleNotExist";
        }

        if(!articleService.isUpdatable(article, httpSession))
            return "qna/updateImpossible";

        articleService.deleteById(index);
        return "redirect:/";
    }
}
