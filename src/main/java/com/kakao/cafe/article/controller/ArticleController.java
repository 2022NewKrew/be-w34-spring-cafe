package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.article.dto.ArticleListDTO;
import com.kakao.cafe.article.dto.ArticleViewDTO;
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
        User user;
        //로그인 상태가 아닌경우
        if((user = (User) session.getAttribute("sessionedUser")) == null){
            throw new ArticleNotLoggedInException("로그인 상태에서만 게시글을 작성할 수 있습니다.");
        }

        //로그인된 사용자가 아닌 다른 사용자를 글쓴이로 작성한경우
        if(!user.getUserId().equals(articleCreateDTO.getUserId())){
            throw new ArticleNotMatchedUser("로그인된 사용자와 글쓴이가 다릅니다.");
        }

        //로그인된 사용자의 이름을 지정
        articleCreateDTO.setName(user.getName());

        articleService.articleCreate(articleCreateDTO);
        return "redirect:/";
    }


    //글작성폼
    @GetMapping(value = "/qna/form.html") //form으로 수정해야함
    public String writeArticle(Model model, HttpSession session){
        User user;
        //로그인 상태가 아닌경우
        if((user = (User) session.getAttribute("sessionedUser")) == null){
            throw new ArticleNotLoggedInException("로그인 상태에서만 게시글을 작성할 수 있습니다.");
        }

        model.addAttribute("userId", user.getUserId());
        return "/qna/form";
    }

    //index.html에 노출되는 질문리스트
    @GetMapping(value = {"/", "/index"})
    public String showArticleList(Model model, HttpSession session) {
        String loggedInUserId = null;
        if(session.getAttribute("sessionedUser") != null) {
            loggedInUserId = ((User) session.getAttribute("sessionedUser")).getUserId();
        }
        if(loggedInUserId != null){
            model.addAttribute("loggedInUserName", loggedInUserId);
        }

        List<Article> articles = articleService.getAllArticles();
        List<ArticleListDTO> articleListDTO = articles.stream().map(ArticleListDTO::new).collect(Collectors.toList());
        model.addAttribute("articles", articleListDTO);

        return "index";
    }

    //상세 질문글 확인
    @GetMapping(value = "/qnas/{sequence}")
    public String showArticle(@PathVariable("sequence") Long sequence, Model model, HttpSession session){

        //로그인 상태가 아닌경우
        if(session.getAttribute("sessionedUser") == null){
            throw new ArticleNotLoggedInException("로그인 상태에서만 상세글을 볼 수 있습니다.");
        }


        Article article = articleService.getArticleBySequence(sequence);
        ArticleViewDTO articleViewDTO = new ArticleViewDTO(article);

        model.addAttribute("userId", articleViewDTO.getUserId());
        model.addAttribute("name", articleViewDTO.getName());
        model.addAttribute("title", articleViewDTO.getTitle());
        model.addAttribute("createdAt", articleViewDTO.getCreatedAt());
        model.addAttribute("contents", articleViewDTO.getContents());
        return "/qna/show";
    }


    //상세 질문글 확인
    @DeleteMapping(value = "/qnas/delete/{userId}")
    public String showArticle(@PathVariable("userId") String userId, HttpSession session){
        User user;
        //로그인 상태가 아닌경우
        if((user = (User) session.getAttribute("sessionedUser")) == null){
            throw new ArticleNotLoggedInException("로그인 상태에서만 글 삭제가 가능합니다.");
        }

        if(!user.getUserId().equals(userId)){
            throw new ArticleNotMatchedUser("로그인된 사용자가 작성한 글이 아닙니다.");
        }

        //클라이언트 개발자모드에서 session 에 저장된 user.getUserId() 와 같은 값을 href에서 다른사람이 쓴 글을 대상으로 조작한다면 여기까지 통과할 것이니 그것에 대한 예외처리도 추가해야함.


        //service에서 삭제메소드를 추가해야함

        return "redirect:/";
    }
}
