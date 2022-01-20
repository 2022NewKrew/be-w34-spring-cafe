package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.Reply;
import com.kakao.cafe.article.dto.*;
import com.kakao.cafe.article.exception.ArticleNotLoggedInException;
import com.kakao.cafe.article.exception.ArticleNotMatchedUser;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticleController {
    ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    //새로운 질문 생성
    @PostMapping(value = "/qna/create")
    public String createArticle(ArticleCreateDTO articleCreateDTO, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");

        //로그인된 사용자가 아닌 다른 사용자를 글쓴이로 작성한경우
        if(!user.equalsUserId(articleCreateDTO.getUserId())){
            throw new ArticleNotMatchedUser("로그인된 사용자와 글쓴이가 다릅니다.");
        }

        articleService.articleCreate(articleCreateDTO);
        return "redirect:/";
    }


    //글작성폼
    @GetMapping(value = "/qna/form")
    public String writeArticle(Model model, HttpSession session){
        return "/qna/form";
    }

    //index.html에 노출되는 질문리스트
    @GetMapping(value = {"/", "/index"})
    public String showArticleList(Model model, HttpSession session) {
        List<Article> articles = articleService.getAllArticles();
        List<ArticleListDTO> articleListDTO = articles.stream().map(ArticleListDTO::new).collect(Collectors.toList());
        model.addAttribute("articles", articleListDTO);

        return "index";
    }

    //상세 질문글 확인
    @GetMapping(value = "/qnas/{sequence}")
    public String showArticle(@PathVariable("sequence") Long sequence, Model model, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");

        Article article = articleService.getArticleBySequence(sequence);
        ArticleViewDTO articleViewDTO = new ArticleViewDTO(article);

        model.addAttribute("article", articleViewDTO);

        //글 작성자인 경우만 수정, 삭제 옵션이 보이도록 구성.
        if(user.equalsUserId(articleViewDTO.getUserId())){
            model.addAttribute("isWriterOfArticle", true);
        }

        List<ReplyViewDTO> replies = articleService.getReplies(sequence);

        //isWriterOfReply적용
        replies.stream().forEach((reply) -> {reply.setWriterOfReply(user.equalsUserId(reply.getUserId()));});
        model.addAttribute("replies", replies);

        return "/qna/show";
    }

    //글 수정시 폼에 원본글 불러오기
    @GetMapping(value = "/qnas/update/{sequence}")
    public String updateArticleForm(@PathVariable("sequence") Long sequence, Model model, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");

        Article article = articleService.getArticleBySequence(sequence);

        if(!user.equalsUserId(article.getUserId())){
            throw new ArticleNotMatchedUser("로그인된 사용자가 작성한 글이 아닙니다.");
        }

        model.addAttribute("article", article);

        return "/qna/updateform";
    }

    //글 수정하기
    @PutMapping(value = "/qnas/update/apply")
    public String updateArticle(ArticleUpdateDTO articleUpdateDTO, HttpSession session) {
        User user = (User) session.getAttribute("sessionedUser");
        Article article = articleService.getArticleBySequence(articleUpdateDTO.getSequence());

        if(!user.equalsUserId(article.getUserId())){
            throw new ArticleNotMatchedUser("다른 사람의 글을 수정할 수 없다.");
        }

        articleService.articleUpdate(articleUpdateDTO);

        return "redirect:/qnas/" + articleUpdateDTO.getSequence();
    }

    //작성글 삭제
    @DeleteMapping(value = "/qnas/delete/{sequence}")
    public String showArticle(@PathVariable("sequence") Long sequence, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");
        Article article = articleService.getArticleBySequence(sequence);

        if(!user.equalsUserId(article.getUserId())){
            throw new ArticleNotMatchedUser("로그인된 사용자가 작성한 글이 아닙니다.");
        }

        articleService.articleDelete(article.getSequence());

        return "redirect:/";
    }

    //새로운 댓글 생성
    @PostMapping(value = "/qnas/reply/create")
    public String createReply(ReplyCreateDTO replyCreateDTO, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");

        //로그인된 사용자가 아닌 다른 사용자를 글쓴이로 작성한경우
        if(!user.equalsUserId(replyCreateDTO.getUserId())){
            throw new ArticleNotMatchedUser("로그인된 사용자와 글쓴이가 다릅니다.");
        }

        articleService.replyCreate(replyCreateDTO);
        return "redirect:/qnas/" + replyCreateDTO.getArticleSeq();
    }
}
