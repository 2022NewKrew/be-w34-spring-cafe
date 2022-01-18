package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.Auth;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.user.UserSessionDto;
import com.kakao.cafe.exception.InputDataException;
import com.kakao.cafe.exception.NullSessionException;
import com.kakao.cafe.exception.UserMismatchException;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/post")
    @Auth
    public String form(HttpSession session) {
        return "./qna/form";
    }

    @PostMapping("/articles/question")
    public String question(@Valid ArticleDto articleDto, BindingResult bindingResult, HttpSession session) throws InputDataException{
        if (bindingResult.hasErrors()) {
            throw new InputDataException("입력이 올바르지 않습니다.");
        }
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        articleDto.setUserId(sessionedUser.getUserId());
        articleDto.setWriter(sessionedUser.getName());
        articleService.addArticle(articleDto);
        logger.info("게시글 등록 완료 : {}", articleDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String questionList(Model model) {
        List<ArticleDto> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "./qna/list";
    }

    @GetMapping("/articles/{id}")
    @Auth
    public String showArticle(@PathVariable int id, Model model) {
        ArticleDto articleDto = articleService.getArticle(id);
        model.addAttribute("article", articleDto);
        return "./qna/show";
    }

    @GetMapping("/articles/{id}/update")
    public String getArticleForm(@PathVariable int id, Model model, HttpSession session) throws UserMismatchException {
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        ArticleDto article = articleService.getArticle(id);
        if (!article.getUserId().equals(sessionedUser.getUserId())) {
            throw new UserMismatchException("다른 사용자의 게시글을 수정할 수 없습니다.");
        }
        model.addAttribute("article",article);
        return "./qna/updateForm";
    }

    @PutMapping("/articles/{id}/update")
    public String updateArticle(@PathVariable int id, ArticleDto articleDto) {
        articleDto.setId(id);
        articleService.updateArticle(articleDto);
        return "redirect:/";
    }

    @DeleteMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable int id, String userId, HttpSession session) throws UserMismatchException {
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        if (!userId.equals(sessionedUser.getUserId())) {
            throw new UserMismatchException("다른 사용자의 게시글을 삭제할 수 없습니다.");
        }
        articleService.deleteArticle(id);
        return "redirect:/";
    }

}
