package com.kakao.cafe.controller;


import com.kakao.cafe.auth.LoginCheck;
import com.kakao.cafe.dto.article.ArticleReqDto;
import com.kakao.cafe.dto.article.ArticleResDto;
import com.kakao.cafe.dto.article.ArticleUpdateDto;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public String createPost(String title, String contents, @LoginCheck SessionUser sessionUser){
        ArticleReqDto articleReqDto = ArticleReqDto.builder()
                .writer(sessionUser.getUserId())
                .title(title)
                .contents(contents)
                .build();
        articleService.addArticle(articleReqDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticleList(Model model){
        model.addAttribute("articles", articleService.findArticles());
        return "index";
    }

    @GetMapping("/articles/form")
    public String showArticleForm(@LoginCheck SessionUser sessionUser){
        return "article/form";
    }

    @GetMapping("/articles/{articleId}")
    public String showArticle(@PathVariable Long articleId, Model model, @LoginCheck SessionUser sessionUser){
        model.addAttribute("article", articleService.findArticleById(articleId));
        return "article/show";
    }

    @GetMapping("/articles/{articleId}/form")
    public String showArticleUpdateForm(@PathVariable Long articleId, Model model, @LoginCheck SessionUser sessionUser){

        ArticleResDto articleResDto = articleService.findArticleById(articleId);
        if(!sessionUser.getUserId().equals(articleResDto.getWriter())){
            // 에러 페이지로 이동하게 수정
            System.out.println("현재 유저: " + sessionUser.getUserId() + " 글쓴이 : " + articleResDto.getWriter());
            return "redirect:/articles/{articleId}";
        }
        model.addAttribute("article",articleResDto);
        return "article/updateForm";
    }

    @PutMapping("/articles/{articleId}")
    public String updateArticle(@PathVariable Long articleId, String title, String contents, @LoginCheck SessionUser sessionUser){
        ArticleResDto articleResDto = articleService.findArticleById(articleId);
        if(!articleResDto.getWriter().equals(sessionUser.getUserId())){
            //에러
            System.out.println("현재 유저: " + sessionUser.getUserId() + " 글쓴이 : " + articleResDto.getWriter());
            System.out.println("수정할 수 없습니다.");
            return "redirect:/articles/{articleId}";
        }

        ArticleUpdateDto articleUpdateDto = ArticleUpdateDto.builder()
                .articleId(articleId)
                .writer(sessionUser.getUserId())
                .title(title)
                .contents(contents)
                .build();
        articleService.updateArticle(articleUpdateDto);
        return "redirect:/articles/{articleId}";
    }
}
