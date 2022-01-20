package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.dto.ReplyContentsDto;
import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final UserService userService;
    private final ReplyService replyService;

    public ArticleController(ArticleService articleService, UserService userService, ReplyService replyService) {
        this.articleService = articleService;
        this.userService = userService;
        this.replyService = replyService;
    }

    @GetMapping("/form")
    public String mainPage(Model model, HttpSession session) {
        String sessionUserId;

        try {
            sessionUserId = userService.getUserIdFromSession(session);
        } catch (NoSuchElementException e) { return "redirect:/"; }

        model.addAttribute("writer", sessionUserId);

        return "qna/form";
    }

    @PostMapping("/questions")
    public String postArticles(ArticlePostDto article, HttpSession session) {
        String writerId = userService.getUserIdFromSession(session);
        article.setWriter(writerId);
        try {
            articleService.post(article);
        } catch (SQLException e) {
            logger.error("/articles/questions, failed to create article (article = {})", article, e);
            return "redirect:/";
        } catch (NoSuchElementException e) {
            logger.info("/articles/questions, failed to create article. writer(id = {}) does not exist", article.getWriter(), e);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable int id, Model model) {
        ArticleDto article;
        List<ReplyDto> replyDtoList;

        try {
            article = articleService.findById(id);
            replyDtoList = replyService.getReplyListOfArticle(id);
            model.addAttribute("article", article);
            model.addAttribute("reply", replyDtoList);
        } catch (NoSuchElementException e) {
            logger.error("/articles/index, article id = {}", id, e);
            return "redirect:/";
        }

        return "qna/show";
    }

    @PutMapping("/{id}")
    public String modifyArticle(@PathVariable int id, ArticlePostDto articlePostDto) {
        try {
            articleService.update(id, articlePostDto);
        } catch (NoSuchElementException e) {
            return "redirect:/";
        }

        return String.format("redirect:/articles/%d", id);
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable int id) {
        try {
            articleService.delete(id);
        } catch (NoSuchElementException e) {
            return "redirect:/";
        }

        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    public String modifyForm(@PathVariable int id, Model model, HttpSession session) {
        ArticleDto articleDto = articleService.findById(id);
        String writerId = articleDto.getWriter();

        if (!userService.checkSessionUser(writerId, session)) { // 다른 작성자의 글 수정 불가
            return "redirect:/";
        }

        model.addAttribute("writer", writerId);
        model.addAttribute("modify", id);

        return "qna/form";
    }

    // aid를 id로 갖는 ARTICLE에 댓글 작성
    @PostMapping("/{aid}/reply")
    public String reply(@PathVariable int aid, ReplyContentsDto replyContentsDto, HttpSession session) {
        try {
            replyService.insertReply(aid, userService.getUserIdFromSession(session), replyContentsDto);
        } catch (SQLException e) {

        }
        return String.format("redirect:/articles/%d", aid);
    }

    @DeleteMapping("/{aid}/reply/{id}")
    public String deleteReply(@PathVariable int aid, @PathVariable int id, HttpSession session) {
        replyService.deleteReply(id);

        return String.format("redirect:/articles/%d", aid);
    }
}
