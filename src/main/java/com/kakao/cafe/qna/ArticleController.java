package com.kakao.cafe.qna;

import com.kakao.cafe.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
        User user = getSessionedUser();
        if (user == null) {
            return "/user/login";
        }

        Article article = new Article(
                user.getUserId(),
                articleDto.getTitle(),
                articleDto.getContents());
        articleService.saveArticle(article);

        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String articleView(Model model, @PathVariable("id") Integer id) {
        Article article = articleService.findArticleById(id);
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

    @GetMapping("/articles/{id}/edit")
    public String questionEditForm(Model model, @PathVariable("id") Integer id) {
        Article article = articleService.findArticleById(id);

        // 글 작성자 ID와 수정 요청자 ID가 일치해야 함
        User user = getSessionedUser();
        if (user == null || !user.getUserId().equals(article.getWriter())) {
            model.addAttribute("article", article);
            return "/qna/show_edit_failed";
        }

        model.addAttribute("article", new ArticleDto(article));
        return "/qna/form_edit";
    }

    @PutMapping("/articles/{id}")
    public String questionEdit(ArticleDto articleDto, @PathVariable("id") Integer id) {
        User user = getSessionedUser();
        if (user == null) return "/user/login";

        articleService.updateArticle(id, articleDto.getTitle(), articleDto.getContents(), user.getUserId());

        return "redirect:/articles/{id}";
    }

    @DeleteMapping("/articles/{id}")
    public String questionDelete(@PathVariable("id") Integer id) {
        User user = getSessionedUser();
        if (user == null) return "/user/login";

        articleService.deleteArticle(id, user.getUserId());

        return "redirect:/";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException() {
        return "/qna/show_edit_failed";
    }

    private User getSessionedUser() {
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return null;
        }

        return (User) value;
    }
}
