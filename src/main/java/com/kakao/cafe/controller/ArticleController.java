package com.kakao.cafe.controller;

import com.kakao.cafe.aop.ArticleAuthCheck;
import com.kakao.cafe.aop.LogInCheck;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/questions")
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @LogInCheck
    @GetMapping("form")
    public String form(){
        return "/qna/form";
    }

    @LogInCheck
    @PostMapping("form")
    public String form(ArticleDTO articleDTO, HttpSession session){
        UserAccount userAccount = (UserAccount) session.getAttribute("sessionedUser");

        articleDTO.setWriter(userAccount.getUserId());
        articleDTO.setParent(-1);

        articleService.join(articleDTO);

        return "redirect:/";
    }

    @LogInCheck
    @GetMapping("/detail/{index}")
    public String datail(@PathVariable("index") int index, Model model){
        Optional<ArticleDTO> optArticle = articleService.findAddedCommentArticle(index);

        if(optArticle.isEmpty()){
            logger.error("[ArticleController > datail] 등록되지 않은 게시글 Id({})로 접근했습니다.", index);
            return "redirect:/";
        }

        ArticleDTO articleDTO = optArticle.get();


        model.addAttribute("article_detail", articleDTO);
        return "/qna/show";
    }

    @LogInCheck
    @ArticleAuthCheck
    @DeleteMapping("/detail/{index}")
    public String delete(@PathVariable("index") int id){

        try {
            articleService.deleteArticle(id);
        } catch (IllegalStateException e) {
            logger.error("[ArticleController > delete] {}", e.getMessage());
        }
        return "redirect:/";
    }

    @LogInCheck
    @ArticleAuthCheck
    @DeleteMapping("/detail/{index}/answers/{id}")
    public String deleteComment(@PathVariable("index") int index, @PathVariable("id") int id){

        articleService.delete(id);
        return "redirect:/";
    }

    @LogInCheck
    @ArticleAuthCheck
    @GetMapping("/detail/{index}/form")
    public String update(@PathVariable("index") int id){
        return "/qna/update_form";
    }

    @LogInCheck
    @ArticleAuthCheck
    @PutMapping("/detail/{index}/form")
    public String update(@PathVariable("index") int id, ArticleDTO articleDTO){
        articleDTO.setId(id);
        articleService.updateArticle(articleDTO);
        return "redirect:/";
    }

    @LogInCheck
    @PostMapping("/detail/{index}/comment")
    public String commentForm(@PathVariable("index") int id, String contents, HttpSession session){
        Optional<Article> findArticle = articleService.findOne(id);
        UserAccount userAccount = (UserAccount) session.getAttribute("sessionedUser");

        if(findArticle.isEmpty()){
            logger.error("[ArticleController > commentForm] 등록되지 않은 게시글 Id({})의 댓글 작성 부분으로 접근했습니다.", id);
            return "redirect:/";
        }

        Article article = findArticle.get();

        ArticleDTO articleDTO = new ArticleDTO(article);
        articleDTO.setParent(id);
        articleDTO.setContents(contents);
        articleDTO.setWriter(userAccount.getUserId());

        articleService.join(articleDTO);

        return "redirect:/questions/detail/" + id;
    }

    @LogInCheck
    @ArticleAuthCheck
    @GetMapping("/detail/{index}/answers/{id}/form")
    public String updateComment(@PathVariable("index") int index, @PathVariable("id") int id){
        return "/qna/update_comment_form";
    }

    @LogInCheck
    @ArticleAuthCheck
    @PutMapping("/detail/{index}/answers/{id}/form")
    public String updateComment(@PathVariable("index") int index, @PathVariable("id") int id, String contents){
        Optional<Article> findArticle = articleService.findOne(id);

        if(findArticle.isEmpty()){
            logger.error("[ArticleController > updateComment] 등록되지 않은 댓글 Id({})의 댓글 수정 부분으로 접근했습니다.", id);
            return "redirect:/";
        }

        ArticleDTO articleDTO = new ArticleDTO(findArticle.get());
        articleDTO.setContents(contents);
        articleService.updateArticle(articleDTO);

        return "redirect:/questions/detail/" + index;
    }
}
