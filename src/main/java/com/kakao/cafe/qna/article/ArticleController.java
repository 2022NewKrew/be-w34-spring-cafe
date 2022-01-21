package com.kakao.cafe.qna.article;

import com.kakao.cafe.qna.comment.Comment;
import com.kakao.cafe.qna.comment.CommentService;
import com.kakao.cafe.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;
    private final HttpSession session;

    @PostMapping
    public String question(ArticleViewDto articleViewDto) {
        User user = getSessionedUser();
        if (user == null) {
            return "/user/login";
        }

        Article article = new Article(
                user.getUserId(),
                articleViewDto.getTitle(),
                articleViewDto.getContents());
        articleService.saveArticle(article);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String articleView(Model model, @PathVariable("id") Integer id) {
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", new ArticleViewDto(article));
        return "/qna/show";
    }

    @GetMapping("/form")
    public String questionForm() {
        return "/qna/form";
    }

    @GetMapping("/{id}/edit")
    public String questionEditForm(Model model, @PathVariable("id") Integer id) {
        Article article = articleService.findArticleById(id);

        // 글 작성자 ID와 수정 요청자 ID가 일치해야 함
        User user = getSessionedUser();
        if (!user.getUserId().equals(article.getWriter())) {
            model.addAttribute("article", article);
            return "/qna/show_edit_failed";
        }

        model.addAttribute("article",
                new ArticleEditDto(article.getId(), article.getTitle(), article.getContents()));
        return "/qna/form_edit";
    }

    @PutMapping("/{id}")
    public String questionEdit(ArticleViewDto articleViewDto, @PathVariable("id") Integer id) {
        User user = getSessionedUser();
        if (user == null) return "/user/login";

        articleService.updateArticle(id, articleViewDto.getTitle(), articleViewDto.getContents(), user.getUserId());

        return "redirect:/articles/{id}";
    }

    @DeleteMapping("/{id}")
    public String questionDelete(@PathVariable("id") Integer id) {
        User user = getSessionedUser();
        if (user == null) return "/user/login";

        // 글 작성자 ID와 삭제 요청자 ID가 일치해야 함
        Article article = articleService.findArticleById(id);
        if (!user.getUserId().equals(article.getWriter())) {
            log.debug("게시글 작성자와 삭제 요청자가 일치하지 않습니다. {}, {}", user.getUserId(), article.getWriter());
            return "/qna/show_edit_failed";
        }

        // 게시글 작성자와 댓글 작성자가 다르면 삭제할 수 없다
        List<Comment> comments = article.getComments();
        for (Comment comment: comments) {
            if (!comment.getWriter().equals(article.getWriter())) {
                log.debug("게시글 작성자와 댓글 작성자가 일치하지 않습니다. {}, {}", comment.getWriterId(), article.getId());
                return "/qna/show_edit_failed";
            }
        }

        // 게시글을 삭제할 수 있을 경우, 댓글을 모두 삭제한다
        for (Comment comment: comments) {
            commentService.deleteComment(comment);
        }

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
