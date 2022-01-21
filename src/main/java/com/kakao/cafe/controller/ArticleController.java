package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.Auth;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticlePageInfoDto;
import com.kakao.cafe.dto.reply.ReplyDto;
import com.kakao.cafe.dto.user.UserSessionDto;
import com.kakao.cafe.exception.InputDataException;
import com.kakao.cafe.exception.UserMismatchException;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;
    private final ReplyService replyService;

    @GetMapping("/articles/post")
    @Auth
    public String form() {
        return "./qna/form";
    }

    @PostMapping("/articles/question")
    public String question(@Valid ArticleDto articleDto, BindingResult bindingResult, HttpSession session) throws InputDataException {
        if (bindingResult.hasErrors()) {
            throw new InputDataException("입력이 올바르지 않습니다.");
        }
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        articleDto.setUserId(sessionedUser.getUserId());
        articleDto.setWriter(sessionedUser.getName());
        articleService.addArticle(articleDto);
        log.info("게시글 등록 완료 : {}", articleDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String questionList(Model model) {
        List<ArticleDto> articles = articleService.getArticles(1);
        ArticlePageInfoDto articlePageInfoDto = articleService.getCurPageInfo(1);
        model.addAttribute("articles", articles);
        model.addAttribute("articlePageInfoDto", articlePageInfoDto);
        return "./qna/list";
    }

    @GetMapping("/{page}")
    public String questionList(@PathVariable int page, Model model) {
        List<ArticleDto> articles = articleService.getArticles(page);
        ArticlePageInfoDto articlePageInfoDto = articleService.getCurPageInfo(page);
        model.addAttribute("articles", articles);
        model.addAttribute("articlePageInfoDto", articlePageInfoDto);
        return "./qna/list";
    }

    @GetMapping("/articles/{articleId}")
    @Auth
    public String showArticle(@PathVariable int articleId, Model model) {
        ArticleDto articleDto = articleService.getArticle(articleId);
        List<ReplyDto> replyList = replyService.getReplyList(articleId);
        model.addAttribute("article", articleDto);
        model.addAttribute("replyList", replyList);
        model.addAttribute("countOfReply", replyList.size());
        return "./qna/show";
    }

    @GetMapping("/articles/{articleId}/update")
    public String getArticleForm(@PathVariable int articleId, Model model, HttpSession session) throws UserMismatchException {
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        ArticleDto article = articleService.getArticle(articleId);
        if (!article.getUserId().equals(sessionedUser.getUserId())) {
            throw new UserMismatchException("다른 사용자의 게시글을 수정할 수 없습니다.");
        }
        model.addAttribute("article", article);
        return "./qna/updateForm";
    }

    @PutMapping("/articles/{articleId}/update")
    public String updateArticle(@PathVariable int articleId, ArticleDto articleDto) {
        articleDto.setArticleId(articleId);
        articleService.updateArticle(articleDto);
        return "redirect:/";
    }

    @DeleteMapping("/articles/{articleId}/delete")
    public String deleteArticle(@PathVariable int articleId, String userId, HttpSession session) throws UserMismatchException {
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        if (!userId.equals(sessionedUser.getUserId())) {
            throw new UserMismatchException("다른 사용자의 게시글을 삭제할 수 없습니다.");
        }
        articleService.deleteArticle(articleId);
        return "redirect:/";
    }

    @GetMapping("/articles/next/{curEndPage}")
    public String getNextArticlePage(@PathVariable int curEndPage) {
        log.info("현재 마지막 페이지 : {}", curEndPage);
        if (articleService.hasNextPage(curEndPage)) {
            return "redirect:/" + (curEndPage + 1);
        }
        return "redirect:/" + curEndPage;
    }

    @GetMapping("/articles/prev/{curStartPage}")
    public String getPrevArticlePage(@PathVariable int curStartPage) {
        if (curStartPage == 1) {
            return "redirect:/";
        }
        return "redirect:/" + (curStartPage-1);
    }

}
