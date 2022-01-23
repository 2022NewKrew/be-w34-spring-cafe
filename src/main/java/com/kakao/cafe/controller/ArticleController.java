package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @PostMapping("/questions")
    public String uploadArticle(ArticleDto articleDto, HttpSession httpSession) {
        articleDto.setUserId((Long)httpSession.getAttribute("sessionUserId"));
        articleDto.setWriter((String)httpSession.getAttribute("sessionUserName"));

        articleService.uploadArticle(articleDto);

        return "redirect:/";
    }

    @GetMapping("/questions/write-page")
    public String writeArticle(@RequestParam("writer") String writer, Model model) {
        model.addAttribute("writer", writer);

        return "/post/form";
    }


    @GetMapping("/")
    public String articleList(Model model) {
        List<ArticleDto> articleDtos = articleService.allArticles();
        model.addAttribute("articles", articleDtos);
        model.addAttribute("total", articleDtos.size());

        return "/index";
    }

    @GetMapping("/questions/{id}")
    public String retrieveArticle(@PathVariable("id") Long articleId, Model model) {
        ArticleDto articleDto = articleService.retrieveArticle(articleId);
        List<ReplyDto> replyDtos = replyService.repliesByArticle(articleId);

        model.addAttribute("article", articleDto);
        model.addAttribute("replies", replyDtos);
        model.addAttribute("count", replyDtos.size());

        return "/post/show";
    }

    @DeleteMapping("/questions/{id}")
    public String deleteArticle(@PathVariable("id") Long articleId, ArticleDto articleDto, HttpSession httpSession) {
        String nickName = (String) httpSession.getAttribute("sessionUserName");
        if(nickName == null || !nickName.equals(articleDto.getWriter())) {
            return "error/permission_fail";
        }

        articleService.deleteArticle(articleId);

        return "redirect:/";
    }

    @GetMapping("/questions/update-page/{id}")
    public String updatePage(@PathVariable("id") Long articleId, @RequestParam("writer") String writer, Model model, HttpSession httpSession) {
        String nickName = (String) httpSession.getAttribute("sessionUserName");
        if(nickName == null || !nickName.equals(writer)) {
            return "error/permission_fail";
        }

        ArticleDto articleDto = articleService.retrieveArticle(articleId);
        model.addAttribute("article", articleDto);

        return "post/update";
    }

    @PutMapping("/questions/{id}")
    public String updateArticle(ArticleDto articleDto) {
        articleService.updateArticle(articleDto);

        return "redirect:/";
    }
}
