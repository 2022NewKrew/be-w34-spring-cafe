package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.LoginRequired;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.article.ArticleDetailResponseDto;
import com.kakao.cafe.dto.article.ArticleListResponseDto;
import com.kakao.cafe.dto.article.ArticleRegisterRequestDto;
import com.kakao.cafe.dto.article.ArticleUpdateFormResponseDto;
import com.kakao.cafe.dto.article.ArticleUpdateRequestDto;
import com.kakao.cafe.dto.reply.ReplyListResponseDto;
import com.kakao.cafe.dto.reply.ReplyRegisterRequestDto;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.mapper.ReplyMapper;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ReplyService replyService;
    private final ArticleMapper articleMapper;
    private final ReplyMapper replyMapper;

    public ArticleController(ArticleService articleService, ReplyService replyService, ArticleMapper articleMapper, ReplyMapper replyMapper) {
        this.articleService = articleService;
        this.replyService = replyService;
        this.articleMapper = articleMapper;
        this.replyMapper = replyMapper;
    }

    @GetMapping("/articles")
    public String requestArticleList(Model model) {
        List<Article> articleList = articleService.getArticleList();
        List<ArticleListResponseDto> dtoList = articleMapper.articleListToArticleListResponseDtoList(articleList);
        model.addAttribute("articles", dtoList);
        return "articles/list";
    }

    @LoginRequired
    @GetMapping("/article/form")
    public String requestArticleRegisterForm() {
        return "articles/form-create";
    }

    @LoginRequired
    @PostMapping("/articles")
    public String requestArticleRegister(@Valid ArticleRegisterRequestDto dto, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Article article = articleMapper.articleRegisterRequestDtoToArticle(dto, user);
        articleService.registerArticle(article);
        return "redirect:/articles";
    }

    @LoginRequired
    @GetMapping("/articles/{articleId}")
    public String requestArticleDetail(@PathVariable UUID articleId, Model model) {
        Article article = articleService.findArticleById(articleId);
        ArticleDetailResponseDto dto = articleMapper.articleToArticleDetailResponseDto(article);
        model.addAttribute("article", dto);
        List<Reply> replyList = replyService.getReplyListOfArticle(articleId);
        List<ReplyListResponseDto> dtoList = replyMapper.replyListToReplyListResponseDtoList(replyList);
        model.addAttribute("replies", dtoList);
        return "articles/detail";
    }

    @LoginRequired
    @GetMapping("/articles/{articleId}/form")
    public String requestArticleUpdateForm(@PathVariable UUID articleId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Article article = articleService.getArticleByIdAndAuthor(articleId, user);
        ArticleUpdateFormResponseDto dto = articleMapper.articleToArticleUpdateFormResponseDto(article);
        model.addAttribute("article", dto);
        return "articles/form-update";
    }

    @LoginRequired
    @PutMapping("/articles/{articleId}")
    public String requestArticleUpdate(@PathVariable UUID articleId, @Valid ArticleUpdateRequestDto dto, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Article article = articleMapper.articleUpdateRequestDtoToArticle(articleId, dto, user);
        articleService.updateArticleByIdAndAuthor(article);
        return String.format("redirect:/articles/%s", articleId.toString());
    }

    @LoginRequired
    @DeleteMapping("/articles/{articleId}")
    public String requestArticleDelete(@PathVariable UUID articleId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        articleService.deleteArticleByIdAndAuthor(articleId, user);
        return "redirect:/";
    }

    @LoginRequired
    @PostMapping("/articles/{articleId}/replies")
    public String requestReplyRegister(@PathVariable UUID articleId, @Valid ReplyRegisterRequestDto dto, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Reply reply = replyMapper.replyRegisterRequestDtoToReply(dto, articleId, user);
        replyService.registerReply(reply);
        return String.format("redirect:/articles/%s", articleId.toString());
    }

    @LoginRequired
    @DeleteMapping("/articles/{articleId}/replies/{replyId}")
    public String requestReplyDelete(@PathVariable UUID articleId, @PathVariable String replyId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        System.out.println(replyId);
        UUID replyUuid = UUID.fromString(replyId);
        System.out.println(replyUuid);
        replyService.deleteReplyByIdAndAuthor(replyUuid, user);
        return String.format("redirect:/articles/%s", articleId.toString());
    }
}
