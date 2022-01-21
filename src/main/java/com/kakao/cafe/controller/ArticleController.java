package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Entity.Comment;
import com.kakao.cafe.dto.article.PostArticleDto;
import com.kakao.cafe.dto.reply.DeleteCommentDto;
import com.kakao.cafe.dto.reply.ShowCommentDto;
import com.kakao.cafe.dto.user.UserInfoDto;
import com.kakao.cafe.exceptions.WrongAccessException;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.reply.CommentService;
import com.kakao.cafe.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;
    private CommentService commentService;

    // 게시물 목록 조회
    @GetMapping("")
    public String showArticles(Model model) {
        model.addAttribute("articles", this.articleService.findAllNotDeleted());
        return "article/list";
    }

    // 게시물 작성 양식 - 로그인 필요
    @GetMapping("/post")
    public String articleForm() {
        return "article/form";
    }

    // 게시물 작성 - 로그인 필요
    @PostMapping("/post")
    public String postArticle(String title, String contents, HttpSession session) {
        UserInfoDto user = (UserInfoDto) session.getAttribute("sessionedUser");
        PostArticleDto postArticleDto = new PostArticleDto(user.getUserId(), user.getName(), title, contents);
        this.articleService.save(postArticleDto);
        return "redirect:/articles";
    }

    // 게시물 상세 조회 - 로그인 필요
    @GetMapping("/{id}")
    public String showArticle(@PathVariable int id, Model model) {
        model.addAttribute("article", this.articleService.findById(id));
        List<ShowCommentDto> comments = this.commentService.findAllByArticleIdAndNotDeleted(id).stream()
                        .map(ShowCommentDto::new).collect(Collectors.toList());
        model.addAttribute("comments", comments);
        model.addAttribute("numOfComments", comments.size());
        return "article/show";
    }

    // 게시물 수정 양식
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable int id, HttpSession session, Model model) throws WrongAccessException {
        this.userService.userValidation(this.articleService.getPostedUserById(id), session);
        model.addAttribute("article", this.articleService.findById(id));
        return "article/updateForm";
    }

    // 게시물 수정 - 로그인 필요, 회원 검사 필요
    @PatchMapping("/{id}/update")
    public String updateArticle(PostArticleDto postArticleDto, @PathVariable int id, HttpSession session) throws WrongAccessException {
        this.userService.userValidation(this.articleService.getPostedUserById(id), session);
        this.articleService.update(postArticleDto, id);
        return "redirect:/articles";
    }

    // 게시물 삭제 - 로그인 필요, 회원 검사 필요
    @DeleteMapping("/{id}/delete")
    public String deleteArticle(@PathVariable int id, HttpSession session) throws WrongAccessException {
        this.userService.userValidation(this.articleService.getPostedUserById(id), session);
        List<DeleteCommentDto> comments = this.commentService.findAllByArticleIdAndNotDeleted(id).stream()
                .map(DeleteCommentDto::new).collect(Collectors.toList());
        for (DeleteCommentDto comment : comments) {
            this.userService.userValidation(comment.getUserId(), session);
        }
        for (DeleteCommentDto comment : comments) {
            this.commentService.delete(comment.getCommentId());
        }
        this.articleService.delete(id);
        return "redirect:/articles";
    }
}
