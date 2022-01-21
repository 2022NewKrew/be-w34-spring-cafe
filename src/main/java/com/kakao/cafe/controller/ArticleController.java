package com.kakao.cafe.controller;


import com.kakao.cafe.dto.reply.ReplyResponse;
import com.kakao.cafe.service.reply.ReplyService;
import com.kakao.cafe.util.auth.LoginCheck;
import com.kakao.cafe.dto.article.ArticleRequest;
import com.kakao.cafe.dto.article.ArticleResponse;
import com.kakao.cafe.dto.article.ArticleUpdateDto;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.util.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ReplyService replyService;

    @PostMapping("/articles")
    public String createPost(String title, String contents, @LoginCheck SessionUser sessionUser){
        ArticleRequest articleRequest = ArticleRequest.builder()
                .writer(sessionUser.getUserId())
                .title(title)
                .contents(contents)
                .build();
        articleService.addArticle(articleRequest);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticleList(Model model){
        model.addAttribute("articles", articleService.findArticles());
        return "index";
    }

    @GetMapping("/articles/form")
    public String showArticleForm(@LoginCheck SessionUser sessionUser){
        if(sessionUser == null){
            return "redirect:/users/login";
        }
        return "article/form";
    }

    @GetMapping("/articles/{articleId}")
    public String showArticle(@PathVariable Long articleId, Model model, @LoginCheck SessionUser sessionUser){
        if(sessionUser == null){
            return "redirect:/users/login";
        }
        List<ReplyResponse> replies = replyService.findReplies(articleId);
        if(replies != null){
            model.addAttribute("replies", replyService.findReplies(articleId));
        }

        model.addAttribute("article", articleService.findArticleById(articleId));
        return "article/show";
    }

    @GetMapping("/articles/{articleId}/form")
    public String showArticleUpdateForm(@PathVariable Long articleId, Model model, @LoginCheck SessionUser sessionUser){

        ArticleResponse articleResponse = articleService.findArticleById(articleId);
        if(sessionUser == null || !sessionUser.getUserId().equals(articleResponse.getWriter())){
            throw new UnauthorizedException("로그인하지 않았거나, 접근 권한이 없습니다.");
        }
        model.addAttribute("article", articleResponse);
        return "article/updateForm";
    }

    @PutMapping("/articles/{articleId}")
    public String amendArticle(@PathVariable Long articleId, String title, String contents, @LoginCheck SessionUser sessionUser){
        ArticleResponse articleResponse = articleService.findArticleById(articleId);
        ArticleUpdateDto articleUpdateDto = ArticleUpdateDto.builder()
                .articleId(articleId)
                .writer(sessionUser.getUserId())
                .title(title)
                .contents(contents)
                .build();
        articleService.modifyArticle(articleUpdateDto, false);
        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("/articles/{articleId}")
    public String removeArticle(@PathVariable Long articleId, @LoginCheck SessionUser sessionUser){
        ArticleResponse articleResponse = articleService.findArticleById(articleId);
        if(sessionUser == null || !sessionUser.getUserId().equals(articleResponse.getWriter())){
            log.error("Login Error : Different ID OR Need to Login");
            return "redirect:/users/login";
        }

        ArticleUpdateDto articleUpdateDto = ArticleUpdateDto.builder()
                .articleId(articleId)
                .writer(articleResponse.getWriter())
                .title(articleResponse.getTitle())
                .contents(articleResponse.getContents() + "삭제")
                .build();
        articleService.modifyArticle(articleUpdateDto, true);
        return "redirect:/";
    }
}
