package com.kakao.cafe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.service.ArticleService;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("")
    public String saveArticle(String title, String content, HttpSession session) {
        articleService.save(new ArticleDto(title, content, (String) session.getAttribute("userId")));
        return "redirect:/";
    }

    @GetMapping("/main")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.findAll().getArticles());
        return "index";
    }

    @GetMapping("/{articleIndex}")
    public String showDetailedArticle(@PathVariable Integer articleIndex, Model model) {
        model.addAttribute("article", articleService.findById(articleIndex));
        return "/qna/show";
    }

    @GetMapping("/write")
    public String createArticle(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/user/login";
        }
        return "/qna/form";
    }

    @GetMapping("/validate/{articleIndex}")
    public String modifyValidation(@PathVariable Integer articleIndex, Model model, HttpSession session) {
        ArticleDto articleDTO = articleService.findById(articleIndex);

        if (!articleDTO.getWriter().equals(session.getAttribute("userId"))) {
            return "redirect:/";
        }
        model.addAttribute("article", articleDTO);
        session.setAttribute("articleIndex", articleIndex);

        return "/qna/modify";
    }

    @PutMapping("/{articleIndex}")
    public String modifyArticle(@PathVariable Integer articleIndex, String title, String content) {
        articleService.modify(articleIndex, title, content);

        return "redirect:/";
    }

    @DeleteMapping("/{index}")
    public String deleteArticle(@PathVariable Integer index) {
        articleService.delete(index);
        return "redirect:/";
    }

    @PostMapping("/reply/{articleIndex}")
    public String reply(@PathVariable Integer articleIndex, String content, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user/login";
        }

        articleService.reply(new ReplyDto(articleIndex, userId, content));

        return "redirect:/articles/{articleIndex}";
    }

    @DeleteMapping("/reply/{replyIndex}")
    public String deleteReply(@PathVariable Integer replyIndex, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        Reply reply = articleService.findReplyById(replyIndex);
        if (userId == null || !reply.getWriter().equals(userId)) {
            return "redirect:/user/login";
        }

        articleService.deleteReply(replyIndex);
        return "redirect:/";
    }
}

