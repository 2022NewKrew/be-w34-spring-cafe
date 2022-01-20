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
        if(!user.getUserId().equals(articleCreateDTO.getUserId())){
            throw new ArticleNotMatchedUser("로그인된 사용자와 글쓴이가 다릅니다.");
        }

        articleService.articleCreate(articleCreateDTO);
        return "redirect:/";
    }


    //글작성폼
    @GetMapping(value = "/qna/form") //form으로 수정해야함
    public String writeArticle(Model model, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");

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

        //글 작성자인 경우
        if(articleViewDTO.getUserId().equals(user.getUserId())){
            model.addAttribute("isWriterOfArticle", true);
        }

        List<ReplyViewDTO> replies = articleService.getReplies(sequence);

        //isWriterOfReply적용
        replies.stream().forEach((reply) -> {reply.setWriterOfReply(reply.getUserId().equals(user.getUserId()));});
        model.addAttribute("replies", replies);

        return "/qna/show";
    }

    //글 수정시 폼에 원본글 불러오기
    @GetMapping(value = "/qnas/update/{sequence}")
    public String updateArticleForm(@PathVariable("sequence") Long sequence, Model model, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");

        Article article = articleService.getArticleBySequence(sequence);
        if(!article.getUserId().equals(user.getUserId())){
            throw new ArticleNotMatchedUser("로그인된 사용자가 작성한 글이 아닙니다.");
        }

        model.addAttribute("userId", article.getUserId());
        model.addAttribute("title", article.getTitle());
        model.addAttribute("contents", article.getContents());
        model.addAttribute("sequence", article.getSequence());

        return "/qna/updateform";
    }

    //글 수정하기
    @PutMapping(value = "/qnas/update/apply")
    public String updateArticle(ArticleUpdateDTO articleUpdateDTO, HttpSession session) {
        User user = (User) session.getAttribute("sessionedUser");
        Article article = articleService.getArticleBySequence(articleUpdateDTO.getSequence());

        if(user == null || !article.getUserId().equals(user.getUserId())){
            throw new ArticleNotMatchedUser("다른 사람의 글을 수정할 수 없다.");
        }

        articleService.articleUpdate(articleUpdateDTO);

        return "redirect:/qnas/" + articleUpdateDTO.getSequence();
    }

    //작성글 삭제
    @DeleteMapping(value = "/qnas/delete/{userId}/{sequence}")
    public String showArticle(@PathVariable("userId") String userId, @PathVariable("sequence") Long sequence, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");


        if(!user.getUserId().equals(userId)){
            throw new ArticleNotMatchedUser("로그인된 사용자가 작성한 글이 아닙니다.");
        }

        //클라이언트 개발자모드에서 session 에 저장된 user.getUserId() 와 같은 값을 href에서 다른사람이 쓴 글을 대상으로 조작한다면 여기까지 통과할 것이니 그것에 대한 예외처리도 추가해야함.
        Article article;
        if( (article = articleService.getArticleBySequence(sequence)) == null || !article.getUserId().equals(user.getUserId()) ){
            throw new ArticleNotMatchedUser("로그인된 사용자가 작성한 글이 아닙니다.");
        }


        //service에서 삭제메소드를 추가해야함
        articleService.articleDelete(article.getSequence());

        return "redirect:/";
    }

    //새로운 댓글 생성
    @PostMapping(value = "/qnas/reply/create")
    public String createReply(ReplyCreateDTO replyCreateDTO, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");

        //로그인된 사용자가 아닌 다른 사용자를 글쓴이로 작성한경우
        if(!user.getUserId().equals(replyCreateDTO.getUserId())){
            throw new ArticleNotMatchedUser("로그인된 사용자와 글쓴이가 다릅니다.");
        }

        articleService.replyCreate(replyCreateDTO);
        return "redirect:/qnas/" + replyCreateDTO.getArticleSeq();
    }
}
