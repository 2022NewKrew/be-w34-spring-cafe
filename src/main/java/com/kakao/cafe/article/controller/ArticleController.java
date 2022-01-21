package com.kakao.cafe.article.controller;

import com.kakao.cafe.annotaion.LoginCheck;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleViewDTO;
import com.kakao.cafe.article.dto.DetailArticleViewDTO;
import com.kakao.cafe.article.dto.QuestionDTO;
import com.kakao.cafe.article.factory.ArticleFactory;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.reply.factory.ReplyFactory;
import com.kakao.cafe.reply.service.ReplyService;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final ReplyService replyService;

    @PostMapping("/questions")
    public String question(QuestionDTO questionDTO, HttpSession session) {
        String userId = (String) session.getAttribute("sessionOfUser");
        User user = userService.findByUserId(userId);

        articleService.question(ArticleFactory.toArticle(user.getId(), user.getName(), questionDTO));
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ArticleViewDTO> articleList = articleService.getAllArticles().stream()
                .map(ArticleViewDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("questions", articleList);

        return "index";
    }

    @LoginCheck
    @GetMapping("/articles/{id}")
    public String getArticleById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", new DetailArticleViewDTO(articleService.findById(id)));
        model.addAttribute("reply", ReplyFactory.toDTO(replyService.findReplyByArticleId(id)));
        return "qna/show";
    }

    @LoginCheck
    @GetMapping("/questions/form")
    public String getQuestionsForm() {
        return "qna/form";
    }

    @LoginCheck
    @DeleteMapping("/articles/{id}")
    public String deleteArticle(@PathVariable("id") Long id, HttpSession session) {
        String userId = (String) session.getAttribute("sessionOfUser");
        User user = userService.findByUserId(userId);

        articleService.deleteArticle(user.getId(), id);
        return "redirect:/";
    }

    @LoginCheck
    @GetMapping("/articles/{id}/form")
    public String getUpdateArticleForm(@PathVariable("id") Long id, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("sessionOfUser");
        User user = userService.findByUserId(userId);
        Article article = articleService.findById(id);
        article.validateAccessUpdateArticle(user.getId());
        model.addAttribute("article", ArticleFactory.toDTO(article.getTitle(), article.getContents()));

        return "/qna/updateForm";
    }


    @LoginCheck
    @PutMapping("/articles/{id}")
    public String updateArticle(@PathVariable("id") Long id, QuestionDTO nQuestionDTO, HttpSession session) {
        String userId = (String) session.getAttribute("sessionOfUser");
        User user = userService.findByUserId(userId);
        Article article = articleService.findById(id);

        articleService.updateArticle(user.getId(),
                ArticleFactory.toArticle(article.getUserFk(), article.getWriter(), article.getId(), article.getCountOfComment(), nQuestionDTO)
        );

        return "redirect:/articles/" + id;
    }


}
