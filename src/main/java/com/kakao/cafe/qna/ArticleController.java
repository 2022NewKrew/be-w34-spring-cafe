package com.kakao.cafe.qna;

import com.kakao.cafe.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final HttpSession session;

    @PostMapping("/questions")
    public String question(ArticleDto articleDto) {
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "/user/login";
        }

        User user = (User) value;

        Article article = new Article(
                null,
                user.getUserId(),
                articleDto.getTitle(),
                articleDto.getContents(),
                0,
                LocalDateTime.now(),
                LocalDateTime.now());
        articleService.saveArticle(article);

        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String articleView(Model model, @PathVariable("index") Integer index) {
        Article article = articleService.findArticleById(index);
        model.addAttribute("article", new ArticleDto(article));
        return "/qna/show";
    }

    @GetMapping("/qna/form")
    public String questionForm() {
        Object value = session.getAttribute("sessionedUser");
        if (value != null) {
            return "/qna/form";
        }
        return "/user/login";
    }
}
