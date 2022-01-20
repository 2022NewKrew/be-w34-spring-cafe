package com.kakao.cafe.controller;


import com.kakao.cafe.util.auth.LoginCheck;
import com.kakao.cafe.dto.article.ArticleReqDto;
import com.kakao.cafe.dto.article.ArticleResDto;
import com.kakao.cafe.dto.article.ArticleUpdateDto;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.util.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Slf4j
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
        model.addAttribute("article", articleService.findArticleById(articleId));
        return "article/show";
    }

    @GetMapping("/articles/{articleId}/form")
    public String showArticleUpdateForm(@PathVariable Long articleId, Model model, @LoginCheck SessionUser sessionUser){

        ArticleResDto articleResDto = articleService.findArticleById(articleId);
        if(sessionUser == null || !sessionUser.getUserId().equals(articleResDto.getWriter())){
            throw new UnauthorizedException("로그인하지 않았거나, 접근 권한이 없습니다.");
        }
        model.addAttribute("article",articleResDto);
        return "article/updateForm";
    }

    @PutMapping("/articles/{articleId}")
    public String updateArticle(@PathVariable Long articleId, String title, String contents, @LoginCheck SessionUser sessionUser){
        ArticleResDto articleResDto = articleService.findArticleById(articleId);
        ArticleUpdateDto articleUpdateDto = ArticleUpdateDto.builder()
                .articleId(articleId)
                .writer(sessionUser.getUserId())
                .title(title)
                .contents(contents)
                .build();
        articleService.updateArticle(articleUpdateDto, false);
        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("/articles/{articleId}")
    public String removeArticle(@PathVariable Long articleId, @LoginCheck SessionUser sessionUser){
        ArticleResDto articleResDto = articleService.findArticleById(articleId);
        if(sessionUser == null || !sessionUser.getUserId().equals(articleResDto.getWriter())){
            log.error("Login Error : Different ID OR Need to Login");
            return "redirect:/users/login";
        }

        ArticleUpdateDto articleUpdateDto = ArticleUpdateDto.builder()
                .articleId(articleId)
                .writer(articleResDto.getWriter())
                .title(articleResDto.getTitle())
                .contents(articleResDto.getContents() + "삭제")
                .build();
        articleService.updateArticle(articleUpdateDto, true);
        return "redirect:/";
    }
}
