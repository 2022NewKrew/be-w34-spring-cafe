package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dto.ArticleModifyDto;
import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.dto.ArticleSaveDto;
import com.kakao.cafe.domain.model.Reply;
import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.exception.InvalidUserException;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;

    @GetMapping("/{id}")
    public String findArticleById(@PathVariable String id, Model model){
        Article article = articleService.findArticleById(id);
        List<Reply> replies = replyService.findAllReplies(id);

        model.addAttribute("article", article);
        model.addAttribute("replies", replies);
        model.addAttribute("replySize", replies.size());
        return "article/view";
    }

    @GetMapping("/post")
    public String articlePostView(){
        return "article/post";
    }

    @PostMapping("/post")
    public String postArticle(@Valid ArticleSaveDto articleSaveDTO, HttpSession session) {
        User sessionedUser = (User) session.getAttribute("sessionedUser");

        articleSaveDTO.setUserId(sessionedUser.getUserId());
        articleService.save(articleSaveDTO);

        return "redirect:/";
    }

    @GetMapping("/modify/{id}")
    public String getModifyArticleView(@PathVariable String id, Model model){
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "article/modify";
    }

    @PutMapping("/modify/{id}")
    public String modifyArticle(@Valid ArticleModifyDto articleModifyDto, @PathVariable String id){;
        articleModifyDto.setId(Integer.parseInt(id.trim()));
        articleService.modifyArticle(articleModifyDto);
        return "redirect:/article/" + id;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteArticle(@PathVariable String id){
        articleService.deleteArticle(id);
        return "redirect:/";
    }
}
